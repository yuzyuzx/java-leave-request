package com.example.leave_request.web.leaveRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LeaveRequestForm {

  @NotNull
  private LocalDate requestDate;

  @NotNull
  private LocalDate startDate;

  @NotNull
  private LocalDate endDate;

  private char status;

//  private LocalDateTime createTime;
//  private LocalDateTime updateTime;
}
