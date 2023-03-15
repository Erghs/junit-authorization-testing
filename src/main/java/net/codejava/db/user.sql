DROP TABLE users
CREATE TABLE users (
                       id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       email    VARCHAR(255) NOT NULL UNIQUE ,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL

)
    ENGINE = InnoDB;