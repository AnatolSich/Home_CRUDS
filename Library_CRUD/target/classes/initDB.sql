DROP TABLE IF EXISTS books;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 1000;
CREATE TABLE books (
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name     VARCHAR(150) NOT NULL,
  author   VARCHAR(50)  NOT NULL,
  release  DATE         NOT NULL,
  quantity INTEGER
)
