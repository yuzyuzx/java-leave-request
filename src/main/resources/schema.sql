create table issues (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  summary VARCHAR(256) NOT NULl,
  description VARCHAR(256) NOT NULl
);

create table leave_request (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  request_date DATE NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  status CHAR(1) NOT NULL,
  create_time TIMESTAMP NOT NULL,
  update_time TIMESTAMP NOT NULL
);
