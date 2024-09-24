package com.example.leave_request.web.leaveRequest;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LeaveRequestForm {
  private LocalDate requestDate;
  private LocalDate startDate;
  private LocalDate endDate;
  private char status;

//  private LocalDateTime createTime;
//  private LocalDateTime updateTime;
}
