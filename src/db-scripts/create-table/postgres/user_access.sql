
DROP TABLE IF EXISTS user_access;

CREATE TABLE user_access (
  access_token VARCHAR(64),
  user_id BIGINT,
  CONSTRAINT user_access_pk PRIMARY KEY (access_token),
  CONSTRAINT user_access_user_fk FOREIGN KEY (user_id) REFERENCES "user"(user_id)
);