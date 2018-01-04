DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 1000;

CREATE TABLE users (
  id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  firstname VARCHAR(45) NOT NULL,
  lastname  VARCHAR(45) NOT NULL,
  email     VARCHAR(45) NOT NULL,
  dob       DATE                DEFAULT NULL
)