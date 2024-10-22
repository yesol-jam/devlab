DROP TABLE IF EXISTS USER_INFO;
CREATE TABLE Users
(
    user_id  VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    `name`   VARCHAR(255),
    email    VARCHAR(255),
    PRIMARY KEY (user_id)
);