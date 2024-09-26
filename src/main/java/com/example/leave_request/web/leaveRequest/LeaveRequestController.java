package com.example.leave_request.web.leaveRequest;

import com.example.leave_request.domain.leaveRequest.LeaveRequestEntity;
import com.example.leave_request.domain.leaveRequest.LeaveRequestService;
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

  @GetMapping("/{requestId}")
  public String showDetail(@PathVariable("requestId") long requestId, Model model) {
    model.addAttribute("leaveRequest", leaveRequestService.findById(requestId));
    return "leave-request/detail";
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
    if(bindingResult.hasErrors()) {
      return showCreationForm(form, id);
    }

    char status = switch(form.getAction()) {
      case "draft" -> '1';
      case "approve" -> '2';
      case "delete" -> '3';
      default -> '0';
    };

    // 削除処理
    if(status == '3') {
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
  public String dbOperation(@Validated ApproveForm form, BindingResult bindingResult, @RequestParam("id") long id) {
    if(bindingResult.hasErrors()) {
      return showApproveForm(form, id);
    }

    // 更新処理
    leaveRequestService.update(id, form.getRequestDate(), form.getStartDate(), form.getEndDate(), '9');
    return "redirect:/";
  }

}
