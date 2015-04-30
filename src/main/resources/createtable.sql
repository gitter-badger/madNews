CREATE TABLE posts
(
  id serial NOT NULL,
  title character(50) NOT NULL,
  content character varying NOT NULL,
  small_img character(50),
  big_img character(50),
  rating integer NOT NULL,
  timestamp timestamp NOT NULL,
  CONSTRAINT posts_pkey PRIMARY KEY (id),
  CONSTRAINT unique_id UNIQUE (id)
)
