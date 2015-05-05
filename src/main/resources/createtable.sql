CREATE TABLE posts
(
  id INT ,
  title char(50),
  content char(500),
  small_img char(50),
  big_img char(50),
  rating int,
  timestamp timestamp,
  CONSTRAINT posts_pkey PRIMARY KEY (id)
);

CREATE TABLE users
(
  id int NOT NULL,
  firstname char(50),
  lastname char(50),
  email char(50) NOT NULL,
  password char(30) NOT NULL,
  role integer NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id)
);