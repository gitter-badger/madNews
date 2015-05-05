CREATE TABLE posts
(
  id int IDENTITY,
  title char(50),
  content char(500),
  smallimg char(50),
  bigimg char(50),
  rating int,
  timestamp int
);

CREATE TABLE users
(
  id int IDENTITY,
  firstname char(50),
  lastname char(50),
  email char(50) NOT NULL,
  password char(30) NOT NULL,
  role integer NOT NULL
);