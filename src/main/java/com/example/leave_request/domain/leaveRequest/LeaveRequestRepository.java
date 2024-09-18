package com.example.leave_request.domain.leaveRequest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeaveRequestRepository {
  @Select("select * from issues")
  List<LeaveRequestEntity> findAll();
}
