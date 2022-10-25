CREATE TABLE IF NOT EXISTS client (
  client_id int AUTO_INCREMENT  PRIMARY KEY,
  user_name varchar(100) NOT NULL,
  password varchar(10) NOT NULL,
  email varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS `log` (
  log_id int AUTO_INCREMENT  PRIMARY KEY,
  message varchar(100) NOT NULL,
  log_type int NOT NULL,
  created_date TIMESTAMP
);