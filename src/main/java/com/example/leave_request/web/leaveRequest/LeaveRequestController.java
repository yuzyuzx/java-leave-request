package com.example.leave_request.web.leaveRequest;

import com.example.leave_request.domain.leaveRequest.LeaveRequestEntity;
import com.example.leave_request.domain.leaveRequest.LeaveRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
@RequestMapping("/leave-request")
public class LeaveRequestController {

  private final LeaveRequestService leaveRequestService;

  @GetMapping("/{id}")
  public String showDetail(@ModelAttribute("approveForm") ApproveForm form, @PathVariable("id") long id) {
    LeaveRequestEntity obj = leaveRequestService.findById(id);

    if(obj == null) {
      return "/";
    }

    form.setRequestDate(obj.getRequestDate());
    form.setStartDate(obj.getStartDate());
    form.setEndDate(obj.getEndDate());

    return "leave-request/detail";
  }

  @PostMapping("/{id}")
  public String deleteFromDetailScreen(@Validated ApproveForm form, @PathVariable("id") long id) {
    leaveRequestService.delete(id);
    return "redirect:approved-list";
  }

  @GetMapping("/approved-list")
  public String showList(Model model) {
    model.addAttribute("approvedLeaveRequestList", leaveRequestService.fetchRequestsByStatus('9'));
    return "leave-request/approved-list";
  }

  @GetMapping("/draft-list")
  public String draftList(Model model) {
    model.addAttribute("draftList", leaveRequestService.fetchRequestsByStatus('1'));
    return "leave-request/draft-list";
  }

  @GetMapping("/pending-approval-list")
  public String pendingApprovalList(Model model) {
    model.addAttribute("pendingApprovalList", leaveRequestService.fetchRequestsByStatus('2'));
    return "leave-request/pending-approval-list";
  }

  @GetMapping("/approval-rejected-list")
  public String approvalRejectedList(Model model) {
    model.addAttribute("pendingApprovalList", leaveRequestService.fetchRequestsByStatus('3'));
    return "leave-request/approval-rejected-list";
  }

  /**
   * 新規作成時のidパラメータは「0」にしている
   */
  @GetMapping("/creationForm")
  public String showCreationForm(@ModelAttribute("leaveRequestForm") LeaveRequestForm form, @RequestParam("id") long id) {
    LeaveRequestEntity obj = leaveRequestService.findById(id);

    if(obj == null) {
      return "leave-request/creationForm";
    }

    form.setRequestDate(obj.getRequestDate());
    form.setStartDate(obj.getStartDate());
    form.setEndDate(obj.getEndDate());

    return "leave-request/creationForm";
  }

  @PostMapping("/creationForm")
  public String dbOperation(@Validated LeaveRequestForm form, BindingResult bindingResult, @RequestParam("id") long id) {
    // 入力チェック
    if(bindingResult.hasErrors()) {
      return showCreationForm(form, id);
    }

    char status = switch(form.getAction()) {
      case "approve" -> '2';
      case "delete" -> '0';
      default -> '1'; // draft（下書き）
    };

    // 削除処理
    if(status == '0') {
      leaveRequestService.delete(id);
      return "redirect:/";
    }

    // 新規登録
    if(id == 0) {
      leaveRequestService.create(form.getRequestDate(), form.getStartDate(), form.getEndDate(), status);
      return "redirect:/";
    }

    // 更新処理
    leaveRequestService.update(id, form.getRequestDate(), form.getStartDate(), form.getEndDate(), status);
    return "redirect:/";
  }

  @GetMapping("/approve-form")
  public String showApproveForm(@ModelAttribute("approveForm") ApproveForm form, @RequestParam("id") long id) {
    LeaveRequestEntity obj = leaveRequestService.findById(id);

    if(obj == null) {
      return "leave-request/approve-form";
    }

    form.setRequestDate(obj.getRequestDate());
    form.setStartDate(obj.getStartDate());
    form.setEndDate(obj.getEndDate());

    return "leave-request/approve-form";
  }

  @PostMapping("/approve-form")
  public String deleteFromApproveForm(@Validated ApproveForm form, @RequestParam("id") long id) {
    leaveRequestService.delete(id);
    return "redirect:/";
  }

}
