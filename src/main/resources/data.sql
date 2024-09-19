insert into issues (summary, description) values ('申請１', '');
insert into issues (summary, description) values ('申請２', '');
insert into issues (summary, description) values ('申請３', '');

insert into
leave_request
  ( request_date, start_date, end_date, status, create_time, update_time )
values
  ( '2024-09-01', '2024-09-01', '2024-09-03', '2',  CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0)),
  ( '2024-10-01', '2024-10-01', '2024-10-03', '2',  CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0));