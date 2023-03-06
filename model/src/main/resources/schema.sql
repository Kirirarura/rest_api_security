-- -----------------------------------------------------
-- Schema module3
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `module3test`;

-- -----------------------------------------------------
-- Schema module3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `module3test` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `module3test`;

-- -----------------------------------------------------
-- Table `module3`.`gift_certificates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`gift_certificates`;

CREATE TABLE IF NOT EXISTS `module3test`.`gift_certificates`
(
    `id`               BIGINT         NOT NULL AUTO_INCREMENT,
    `create_date`      VARCHAR(255)   NOT NULL,
    `description`      VARCHAR(255)   NOT NULL,
    `duration`         INT            NOT NULL,
    `last_update_date` VARCHAR(255)   NOT NULL,
    `name`             VARCHAR(255)   NOT NULL,
    `price`            DECIMAL(19, 2) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `module3`.`revinfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`revinfo`;

CREATE TABLE IF NOT EXISTS `module3test`.`revinfo`
(
    `rev`      INT    NOT NULL AUTO_INCREMENT,
    `revtstmp` BIGINT NULL DEFAULT NULL,
    PRIMARY KEY (`rev`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `module3`.`gift_certificates_aud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`gift_certificates_aud`;

CREATE TABLE IF NOT EXISTS `module3test`.`gift_certificates_aud`
(
    `id`               BIGINT         NOT NULL,
    `rev`              INT            NOT NULL,
    `revtype`          TINYINT        NULL DEFAULT NULL,
    `create_date`      VARCHAR(255)   NULL DEFAULT NULL,
    `description`      VARCHAR(255)   NULL DEFAULT NULL,
    `duration`         INT            NULL DEFAULT NULL,
    `last_update_date` VARCHAR(255)   NULL DEFAULT NULL,
    `name`             VARCHAR(255)   NULL DEFAULT NULL,
    `price`            DECIMAL(19, 2) NULL DEFAULT NULL,
    PRIMARY KEY (`id`, `rev`),
    INDEX `FKe5fioq2e7tskfom4ta0h6id9s` (`rev` ASC) VISIBLE,
    CONSTRAINT `FKe5fioq2e7tskfom4ta0h6id9s`
        FOREIGN KEY (`rev`)
            REFERENCES `module3test`.`revinfo` (`rev`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `module3`.`tags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`tags`;

CREATE TABLE IF NOT EXISTS `module3test`.`tags`
(
    `id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `module3`.`gift_certificates_has_tags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`gift_certificates_has_tags`;

CREATE TABLE IF NOT EXISTS `module3test`.`gift_certificates_has_tags`
(
    `gift_certificate_id` BIGINT NOT NULL,
    `tag_id`              BIGINT NOT NULL,
    INDEX `FKhsugmyqjhxf23qbq5uofci2tn` (`tag_id` ASC) VISIBLE,
    INDEX `FKm1psu36qkk4v13nervo236yy4` (`gift_certificate_id` ASC) VISIBLE,
    CONSTRAINT `FKhsugmyqjhxf23qbq5uofci2tn`
        FOREIGN KEY (`tag_id`)
            REFERENCES `module3test`.`tags` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `FKm1psu36qkk4v13nervo236yy4`
        FOREIGN KEY (`gift_certificate_id`)
            REFERENCES `module3test`.`gift_certificates` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `module3`.`gift_certificates_has_tags_aud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`gift_certificates_has_tags_aud`;

CREATE TABLE IF NOT EXISTS `module3test`.`gift_certificates_has_tags_aud`
(
    `rev`                 INT     NOT NULL,
    `gift_certificate_id` BIGINT  NOT NULL,
    `tag_id`              BIGINT  NOT NULL,
    `revtype`             TINYINT NULL DEFAULT NULL,
    PRIMARY KEY (`rev`, `gift_certificate_id`, `tag_id`),
    CONSTRAINT `FK70b043einglv1746wex6kyjsd`
        FOREIGN KEY (`rev`)
            REFERENCES `module3test`.`revinfo` (`rev`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `module3`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`users`;

CREATE TABLE IF NOT EXISTS `module3test`.`users`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name`  VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `module3`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`orders`;

CREATE TABLE IF NOT EXISTS `module3test`.`orders`
(
    `id`                  BIGINT         NOT NULL AUTO_INCREMENT,
    `price`               DECIMAL(19, 2) NOT NULL,
    `purchase_date`       VARCHAR(255)   NOT NULL,
    `gift_certificate_id` BIGINT         NOT NULL,
    `user_id`             BIGINT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `FK32ql8ubntj5uh44ph9659tiih` (`user_id` ASC) VISIBLE,
    INDEX `FKl0qgtheicxemx7dc9262tp5fy` (`gift_certificate_id` ASC) VISIBLE,
    CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih`
        FOREIGN KEY (`user_id`)
            REFERENCES `module3test`.`users` (`id`),
    CONSTRAINT `FKl0qgtheicxemx7dc9262tp5fy`
        FOREIGN KEY (`gift_certificate_id`)
            REFERENCES `module3test`.`gift_certificates` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `module3`.`orders_aud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`orders_aud`;

CREATE TABLE IF NOT EXISTS `module3test`.`orders_aud`
(
    `id`                  BIGINT         NOT NULL,
    `rev`                 INT            NOT NULL,
    `revtype`             TINYINT        NULL DEFAULT NULL,
    `price`               DECIMAL(19, 2) NULL DEFAULT NULL,
    `purchase_date`       VARCHAR(255)   NULL DEFAULT NULL,
    `gift_certificate_id` BIGINT         NULL DEFAULT NULL,
    `user_id`             BIGINT         NULL DEFAULT NULL,
    PRIMARY KEY (`id`, `rev`),
    INDEX `FKinujab7ljkelflu16c9jjch19` (`rev` ASC) VISIBLE,
    CONSTRAINT `FKinujab7ljkelflu16c9jjch19`
        FOREIGN KEY (`rev`)
            REFERENCES `module3test`.`revinfo` (`rev`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `module3`.`tags_aud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`tags_aud`;

CREATE TABLE IF NOT EXISTS `module3test`.`tags_aud`
(
    `id`      BIGINT       NOT NULL,
    `rev`     INT          NOT NULL,
    `revtype` TINYINT      NULL DEFAULT NULL,
    `name`    VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`, `rev`),
    INDEX `FK1jtbkw86fnuap1gio8ucmbj86` (`rev` ASC) VISIBLE,
    CONSTRAINT `FK1jtbkw86fnuap1gio8ucmbj86`
        FOREIGN KEY (`rev`)
            REFERENCES `module3test`.`revinfo` (`rev`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `module3`.`users_aud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `module3test`.`users_aud`;

CREATE TABLE IF NOT EXISTS `module3test`.`users_aud`
(
    `id`         BIGINT      NOT NULL,
    `rev`        INT         NOT NULL,
    `revtype`    TINYINT     NULL DEFAULT NULL,
    `first_name` VARCHAR(20) NULL DEFAULT NULL,
    `last_name`  VARCHAR(20) NULL DEFAULT NULL,
    PRIMARY KEY (`id`, `rev`),
    INDEX `FKc4vk4tui2la36415jpgm9leoq` (`rev` ASC) VISIBLE,
    CONSTRAINT `FKc4vk4tui2la36415jpgm9leoq`
        FOREIGN KEY (`rev`)
            REFERENCES `module3test`.`revinfo` (`rev`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;