DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    userid  BIGINT PRIMARY KEY auto_increment,
    username VARCHAR(128) UNIQUE,
    enabled  BOOL,
    age BIGINT
);