package com.example.leave_request.domain.leaveRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {

  private final LeaveRequestRepository leaveRequestRepository;

  public List<LeaveRequestEntity> findAll() {
    return leaveRequestRepository.findAll();
  }

  @Transactional
  public void create(LocalDate requestDate, LocalDate startDate, LocalDate endDate, char status) {
    leaveRequestRepository.insert(requestDate, startDate, endDate, status);
  }

  public LeaveRequestEntity findById(long requestId) {
    return leaveRequestRepository.findById(requestId);
  }
}
