package com.example.leave_request.domain.leaveRequest;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface LeaveRequestRepository {
  @Select("select * from leave_request")
  List<LeaveRequestEntity> findAll();

  @Insert("insert into leave_request (request_date, start_date, end_date, status, create_time, update_time) values (#{requestDate}, #{startDate}, #{endDate}, #{status}, CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0))")
  void insert(
    @Param("requestDate") LocalDate requestDate,
    @Param("startDate") LocalDate startDate,
    @Param("endDate") LocalDate endDate,
    @Param("status") char status
  );

  @Select("select * from leave_request where id = #{requestId}")
  LeaveRequestEntity findById(long requestId);

}
