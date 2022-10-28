CREATE TABLE IF NOT EXISTS client (
  client_id int AUTO_INCREMENT PRIMARY KEY,
  user_name varchar(100) NOT NULL UNIQUE,
  password varchar(10) NOT NULL,
  email varchar(100) NOT NULL UNIQUE,
  role varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS log (
  log_id int AUTO_INCREMENT PRIMARY KEY,
  message varchar(100) NOT NULL,
  log_type varchar(100) NOT NULL,
  created_date TIMESTAMP,
  client_id int
);

ALTER TABLE log
ADD FOREIGN KEY (client_id) REFERENCES client(client_id);