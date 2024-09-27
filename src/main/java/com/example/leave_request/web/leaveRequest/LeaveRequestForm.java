package com.example.leave_request.web.leaveRequest;

import com.example.leave_request.domain.leaveRequest.DayCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

// https://ti-tomo-knowledge.hatenablog.com/entry/2018/06/18/094526?_gl=1*h24bch*_gcl_au*NzI4MTk3NzY2LjE3MjIyNTA1NDI.

@Data
@DayCheck
public class LeaveRequestForm implements Serializable {

  @NotNull(message = "申請日は入力必須です")
//  @DayCheck
  private LocalDate requestDate;

  @NotNull(message = "開始日は入力必須です")
// @DayCheck
 private LocalDate startDate;

  @NotNull(message = "終了日は入力必須です")
//  @DayCheck
  private LocalDate endDate;

//  @NotNull
  private String action;

  private char status;
}
