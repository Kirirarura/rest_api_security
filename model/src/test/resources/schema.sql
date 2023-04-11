DROP TABLE IF EXISTS gift_certificates;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS gift_certificate_has_tag;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE gift_certificates
(
    `id`          BIGINT         NOT NULL AUTO_INCREMENT,
    `created`     TIMESTAMP      NOT NULL,
    `description` VARCHAR(255)   NOT NULL,
    `duration`    INT            NOT NULL,
    `updated`     TIMESTAMP      NOT NULL,
    `name`        VARCHAR(255)   NOT NULL,
    `price`       DECIMAL(19, 2) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE tags
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE gift_certificate_has_tag
(
    `gift_certificate_id` BIGINT NOT NULL,
    `tag_id`              BIGINT NOT NULL,
    CONSTRAINT `fk_certificates_has_tags_certificates`
        FOREIGN KEY (`gift_certificate_id`)
            REFERENCES gift_certificates (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_certificates_has_tags_tags1`
        FOREIGN KEY (`tag_id`)
            REFERENCES tags (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `module4`.`users`
-- -----------------------------------------------------

CREATE TABLE users
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT,
    `created`    TIMESTAMP    NOT NULL,
    `status`     VARCHAR(25)  NOT NULL,
    `updated`    TIMESTAMP    NOT NULL,
    `email`      VARCHAR(255) NOT NULL,
    `first_name` VARCHAR(100) NOT NULL,
    `last_name`  VARCHAR(100) NOT NULL,
    `password`   VARCHAR(255) NOT NULL,
    `username`   VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS orders
(
    `id`                  BIGINT         NOT NULL AUTO_INCREMENT,
    `price`               DECIMAL(19, 2) NOT NULL,
    `purchase_date`       TIMESTAMP      NOT NULL,
    `gift_certificate_id` BIGINT,
    `user_id`             BIGINT         NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_orders_gift_certificates`
        FOREIGN KEY (`gift_certificate_id`)
            REFERENCES gift_certificates (`id`)
            ON DELETE SET NULL,
    CONSTRAINT `fk_orders_users`
        FOREIGN KEY (`user_id`)
            REFERENCES users (`id`)
            ON UPDATE RESTRICT
);

-- -----------------------------------------------------
-- Table `module4`.`roles`
-- -----------------------------------------------------

CREATE TABLE roles
(
    `id`      BIGINT       NOT NULL AUTO_INCREMENT,
    `created` TIMESTAMP    NOT NULL,
    `status`  VARCHAR(25)  NOT NULL,
    `updated` TIMESTAMP    NOT NULL,
    `name`    VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `module4`.`user_roles`
-- -----------------------------------------------------

CREATE TABLE user_roles
(
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    CONSTRAINT `fk_user_roles_roles`
        FOREIGN KEY (`role_id`)
            REFERENCES roles (`id`)
            ON DELETE CASCADE,
    CONSTRAINT `fk_user_roles_users`
        FOREIGN KEY (`user_id`)
            REFERENCES users (`id`)
            ON DELETE CASCADE
);