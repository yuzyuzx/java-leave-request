package com.example.leave_request.web.leaveRequest;

import com.example.leave_request.domain.leaveRequest.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

  @GetMapping("creationForm")
  public String showCreationForm(@ModelAttribute LeaveRequestForm form) {
    return "leave-request/creationForm";
  }

  @PostMapping
  public String create(LeaveRequestForm form, Model model) {
    leaveRequestService.create(form.getSummary(), form.getDescription());
    return showList(model);
  }
}
