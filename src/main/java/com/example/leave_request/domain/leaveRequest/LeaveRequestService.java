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

  public List<LeaveRequestEntity> fetchRequestsByStatus(char status) {
    return leaveRequestRepository.fetchRequestsByStatus(status);
  }

  public LeaveRequestEntity findById(long requestId) {
    return leaveRequestRepository.findById(requestId);
  }

  @Transactional
  public void create(LocalDate requestDate, LocalDate startDate, LocalDate endDate, char status) {
    leaveRequestRepository.insert(requestDate, startDate, endDate, status);
  }

  @Transactional
  public void update(long id, LocalDate requestDate, LocalDate startDate, LocalDate endDate, char status) {
    leaveRequestRepository.update(id, requestDate, startDate, endDate, status);
  }

  @Transactional
  public void delete(long id) {
    leaveRequestRepository.delete(id);
  }
}
