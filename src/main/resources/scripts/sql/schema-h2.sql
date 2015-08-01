CREATE SCHEMA IF NOT EXISTS TRAINING;
SET SCHEMA TRAINING;
CREATE TABLE course (
  id BIGINT NOT NULL AUTO_INCREMENT,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  teacher VARCHAR2(255) NOT NULL,
  title VARCHAR2(255) NOT NULL,
  hours INT NOT NULL,
  level INT NOT NULL,

);