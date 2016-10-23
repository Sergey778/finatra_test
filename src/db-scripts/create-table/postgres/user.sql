
DROP SEQUENCE IF EXISTS user_seq;
DROP TABLE IF EXISTS "user";

CREATE SEQUENCE user_seq START WITH 1;

CREATE TABLE "user" (
  user_id BIGINT DEFAULT NEXTVAL('user_seq'),
  user_email VARCHAR(254), -- http://www.rfc-editor.org/errata_search.php?rfc=3696&eid=1690
  user_name VARCHAR(64),
  user_password VARCHAR(60), -- bcrypt output is 60
  user_avalon_login VARCHAR(32),
  CONSTRAINT user_pk PRIMARY KEY (user_id)
);