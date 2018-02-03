DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS user_seq;
DROP SEQUENCE IF EXISTS book_seq;

CREATE SEQUENCE user_seq
  START 1000;
CREATE SEQUENCE book_seq
  START 5000;
CREATE TABLE users (
  id   INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  name VARCHAR(50) NOT NULL,
  dob  DATE        NOT NULL
);
CREATE TABLE books (
  id          INTEGER PRIMARY KEY DEFAULT nextval('book_seq'),
  title       VARCHAR(50) NOT NULL,
  releaseDate DATE        NOT NULL,
  userId      INTEGER     NOT NULL,
  FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE
);