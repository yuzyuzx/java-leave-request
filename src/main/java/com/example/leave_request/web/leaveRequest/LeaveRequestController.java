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


@Controller
@RequiredArgsConstructor
@RequestMapping("/leave-request")
public class LeaveRequestController {

  private final LeaveRequestService leaveRequestService;

  @GetMapping
  public String showList(Model model) {
    model.addAttribute("leaveRequestList", leaveRequestService.findAll());
    return "leave-request/list";
  }

  @GetMapping("/creationForm")
  public String showCreationForm(@ModelAttribute LeaveRequestForm form) {
    return "leave-request/creationForm";
  }

  @PostMapping("/creationForm")
  public String create(@Validated LeaveRequestForm form, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return showCreationForm(form);
    }

    leaveRequestService.create(form.getRequestDate(), form.getStartDate(), form.getEndDate(), form.getStatus());
    return "redirect:/leave-request";
  }

  @GetMapping("/{requestId}")
  public String showDetail(@PathVariable("requestId") long requestId, Model model) {
    model.addAttribute("leaveRequest", leaveRequestService.findById(requestId));
    return "leave-request/detail";
  }

}
