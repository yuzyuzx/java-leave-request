package com.example.leave_request.domain.leaveRequest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Data
public class LeaveRequestEntity {
  private long id;
  private LocalDate requestDate;
  private LocalDate startDate;
  private LocalDate endDate;
  private char status;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}