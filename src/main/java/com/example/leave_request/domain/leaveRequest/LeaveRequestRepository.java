package com.example.leave_request.domain.leaveRequest;

import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface LeaveRequestRepository {
  @Select("select * from leave_request")
  List<LeaveRequestEntity> findAll();

  @Select("select * from leave_request where status = #{status}")
  List<LeaveRequestEntity> fetchRequestsByStatus(char status);

  @Select("select * from leave_request where id = #{requestId}")
  LeaveRequestEntity findById(long requestId);

  @Insert("insert into leave_request (request_date, start_date, end_date, status, create_time, update_time) values (#{requestDate}, #{startDate}, #{endDate}, #{status}, CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0))")
  void insert(
    @Param("requestDate") LocalDate requestDate,
    @Param("startDate") LocalDate startDate,
    @Param("endDate") LocalDate endDate,
    @Param("status") char status
  );

  @Update("update leave_request set request_date = #{requestDate}, start_date = #{startDate}, end_date = #{endDate}, status = #{status}, update_time = CURRENT_TIMESTAMP(0) where id = #{id}")
  void update(
    @Param("id") long id,
    @Param("requestDate") LocalDate requestDate,
    @Param("startDate") LocalDate startDate,
    @Param("endDate") LocalDate endDate,
    @Param("status") char status
  );

  @Delete("delete from leave_request where id = #{id}")
  void delete(
    @Param("id") long id
  );

}
