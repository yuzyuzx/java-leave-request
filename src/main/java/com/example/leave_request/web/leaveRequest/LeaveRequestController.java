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

  @GetMapping
  public String showList(Model model) {
    model.addAttribute("approvedLeaveRequestList", leaveRequestService.fetchRequestsByStatus('9'));
    return "leave-request/list";
  }

  @GetMapping("/draft-list")
  public String draftList(Model model) {
    model.addAttribute("draftLeaveRequestList", leaveRequestService.fetchRequestsByStatus('1'));
    return "leave-request/draft-list";
  }

  /**
   * 新規作成時のidパラメータは「0」にしている
   * @param form
   * @param id
   * @return
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

    if(status == '3') {
      // 削除処理
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

  @GetMapping("/{requestId}")
  public String showDetail(@PathVariable("requestId") long requestId, Model model) {
    model.addAttribute("leaveRequest", leaveRequestService.findById(requestId));
    return "leave-request/detail";
  }

}
