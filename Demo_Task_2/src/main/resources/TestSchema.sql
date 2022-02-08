DROP TABLE IF EXISTS address;

CREATE TABLE address
(
	addressID  BIGINT PRIMARY KEY auto_increment,
    userID  BIGINT,
    city VARCHAR(128),
    state VARCHAR(128),
    country VARCHAR(128)
);