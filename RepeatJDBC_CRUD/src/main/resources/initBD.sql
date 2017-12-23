DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 10000;
CREATE TABLE users (
  id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  firstName VARCHAR(50)        NOT NULL,
  lastName  VARCHAR(50)        NOT NULL,
  email     VARCHAR(50) UNIQUE NOT NULL,
  dob       DATE                DEFAULT NULL
);

