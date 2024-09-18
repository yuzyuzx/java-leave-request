package com.example.leave_request.domain.leaveRequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LeaveRequestEntity {
  private long id;
  private String summary;
  private String description;
}
