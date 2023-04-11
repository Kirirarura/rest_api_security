INSERT INTO tags (`name`)
VALUES ('tagName1');
INSERT INTO tags (`name`)
VALUES ('tagName2');
INSERT INTO tags (`name`)
VALUES ('tagName3');

INSERT INTO gift_certificates (`name`, description, price, duration, created, updated)
VALUES ('giftCertificate1', 'description1', 10.1, 1, '2020-08-29T06:12:15.156',
        '2020-08-29T06:12:15.156');
INSERT INTO gift_certificates (`name`, description, price, duration, created, updated)
VALUES ('giftCertificate2', 'description2', 20.1, 2, '2020-08-29T06:12:15.156',
        '2020-08-29T06:12:15.156');
INSERT INTO gift_certificates (`name`, description, price, duration, created, updated)
VALUES ('giftCertificate3', 'description3', 30.1, 3, '2020-08-29T06:12:15.156',
        '2020-08-29T06:12:15.156');

INSERT INTO gift_certificate_has_tag (gift_certificate_id, tag_id)
VALUES (1, 1);
INSERT INTO gift_certificate_has_tag (gift_certificate_id, tag_id)
VALUES (1, 2);
INSERT INTO gift_certificate_has_tag (gift_certificate_id, tag_id)
VALUES (2, 2);
INSERT INTO gift_certificate_has_tag (gift_certificate_id, tag_id)
VALUES (2, 3);

INSERT INTO users(created, status, updated, email, first_name, last_name, password, username)
VALUES ('2020-08-29T06:12:15.156', 'ACTIVE', '2020-08-29T06:12:15.156',
        'emailOfUser1gmail.com', 'first_name', 'last_name',
        '$2a$04$pdPdz98.SMXrDE2/0NZD7OWwOzzb/d0w75KqJRTxNBX5El8oQdhP6', 'user1');
INSERT INTO users(created, status, updated, email, first_name, last_name, password, username)
VALUES ('2020-08-29T06:12:15.156', 'ACTIVE', '2020-08-29T06:12:15.156',
        'emailOfUser2gmail.com', 'first_name', 'last_name',
        '$2a$04$pdPdz98.SMXrDE2/0NZD7OWwOzzb/d0w75KqJRTxNBX5El8oQdhP6', 'user2');
INSERT INTO users(created, status, updated, email, first_name, last_name, password, username)
VALUES ('2020-08-29T06:12:15.156', 'ACTIVE', '2020-08-29T06:12:15.156',
        'emailOfUser3gmail.com', 'first_name', 'last_name',
        '$2a$04$pdPdz98.SMXrDE2/0NZD7OWwOzzb/d0w75KqJRTxNBX5El8oQdhP6', 'user3');

INSERT INTO orders (price, purchase_date, gift_certificate_id, user_id)
VALUES (10.1, '2020-08-29T06:12:15.156', 1, 1);
INSERT INTO orders (price, purchase_date, gift_certificate_id, user_id)
VALUES (20.1, '2020-08-29T06:12:15.156', 2, 1);
INSERT INTO orders (price, purchase_date, gift_certificate_id, user_id)
VALUES (30.1, '2020-08-29T06:12:15.156', 3, 1);