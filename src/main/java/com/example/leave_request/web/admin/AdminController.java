package com.example.leave_request.web.admin;

import com.example.leave_request.domain.leaveRequest.LeaveRequestEntity;
import com.example.leave_request.domain.leaveRequest.LeaveRequestService;
import com.example.leave_request.web.leaveRequest.ApproveForm;
import com.example.leave_request.web.leaveRequest.LeaveRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

  private final LeaveRequestService leaveRequestService;

  @GetMapping("/pending-approval-list")
  public String pendingApprovalList(Model model) {
    model.addAttribute("pendingApprovalList", leaveRequestService.fetchRequestsByStatus('2'));
    return "admin/pending-approval-list";
  }

  @GetMapping("/approve-form")
  public String showApproveForm(@ModelAttribute("approveForm") ApproveForm form, @RequestParam("id") long id) {
    LeaveRequestEntity obj = leaveRequestService.findById(id);

    if(obj == null) {
      return "admin/approve-form";
    }

    form.setRequestDate(obj.getRequestDate());
    form.setStartDate(obj.getStartDate());
    form.setEndDate(obj.getEndDate());

    return "admin/approve-form";
  }

  @PostMapping("/approve-form")
  public String dbOperation(@Validated ApproveForm form, BindingResult bindingResult, @RequestParam("id") long id) {

    if(Objects.equals(form.getAction(), "reject")) {
      leaveRequestService.update(id, form.getRequestDate(), form.getStartDate(), form.getEndDate(), '3');
      return "redirect:/admin/pending-approval-list";

    }

    leaveRequestService.update(id, form.getRequestDate(), form.getStartDate(), form.getEndDate(), '9');
    return "redirect:/admin/pending-approval-list";
  }

}
