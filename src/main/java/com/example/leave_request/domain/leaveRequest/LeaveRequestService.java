package com.example.leave_request.domain.leaveRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {

  private final LeaveRequestRepository leaveRequestRepository;

  public List<LeaveRequestEntity> findAll() {
    return leaveRequestRepository.findAll();
  }
}
