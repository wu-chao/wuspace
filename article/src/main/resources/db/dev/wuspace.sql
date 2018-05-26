
CREATE TABLE user (
  id BIGINT(64) PRIMARY KEY NOT NULL ,
  username VARCHAR(255) UNIQUE NOT NULL ,
  password VARCHAR(16) NOT NULL,
  group_name VARCHAR(50)
);

CREATE TABLE authorities (
  authority VARCHAR(50) PRIMARY KEY NOT NULL,
  username VARCHAR(50) NOT NULL,
  group_name VARCHAR(50)
);

CREATE TABLE groups (
  name VARCHAR(50) PRIMARY KEY NOT NULL
);

CREATE TABLE topic_type (
  id INT PRIMARY KEY NOT NULL ,
  name VARCHAR(50),
  description VARCHAR(255)
);

CREATE TABLE blog (
  id BIGINT(128) PRIMARY KEY NOT NULL ,
  created_at TIMESTAMP,
  updated_at DATETIME,
  deleted_at DATETIME,
  user_id BIGINT(64),
  category VARCHAR(255),
  title VARCHAR(255),
  content LONGTEXT,
  tags VARCHAR(255),
  viewed_times BIGINT(128),
  zan_user_id BIGINT(64),
  cai_user_id BIGINT(64),
  FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE comment (
  id BIGINT(128) PRIMARY KEY NOT NULL ,
  user_id BIGINT(64),
  on_user_id BIGINT(64),
  on_blog_id BIGINT(128),
  content TEXT,
  zan_user_id BIGINT(64),
  cai_user_id BIGINT(64),
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (on_user_id) REFERENCES user (id),
  FOREIGN KEY (on_blog_id) REFERENCES blog (id),
  FOREIGN KEY (zan_user_id) REFERENCES user (id),
  FOREIGN KEY (cai_user_id) REFERENCES user (id)
);

CREATE TABLE reply (
  id BIGINT(128) PRIMARY KEY NOT NULL ,
  user_id BIGINT(64),
  to_user_id BIGINT(64),
  to_comment_id BIGINT(128),
  content TEXT,
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (to_user_id) REFERENCES user (id),
  FOREIGN KEY (to_comment_id) REFERENCES comment (id)
);
