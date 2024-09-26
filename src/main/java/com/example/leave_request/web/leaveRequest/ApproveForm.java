package com.example.leave_request.web.leaveRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ApproveForm {

  @NotNull
  private LocalDate requestDate;

  @NotNull
  private LocalDate startDate;

  @NotNull
  private LocalDate endDate;

  @NotNull
  private String action;

  private char status;
}
