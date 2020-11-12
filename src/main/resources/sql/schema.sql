DROP TABLE IF EXISTS pages;

CREATE TABLE pages
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    url           VARCHAR(250) NOT NULL,
    status        VARCHAR(250) NOT NULL,
    error_message VARCHAR(250) NULL_TO_DEFAULT
);
