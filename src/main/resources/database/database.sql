DROP SCHEMA IF EXISTS shop CASCADE;
DROP TABLE IF EXISTS shop.product;
DROP TABLE IF EXISTS shop.user_roles;
DROP TABLE IF EXISTS shop.roles;
DROP TABLE IF EXISTS shop.users;

DROP SEQUENCE IF EXISTS shop.product_id_seq;
DROP SEQUENCE IF EXISTS shop.roles_id_seq;
DROP SEQUENCE IF EXISTS shop.objects_id_seq;
DROP SEQUENCE IF EXISTS shop.peoples_id_seq;
DROP SEQUENCE IF EXISTS shop.hibernate_sequence;

CREATE SCHEMA shop;

CREATE SEQUENCE IF NOT EXISTS shop.product_id_seq START WITH 1 increment by 1;
CREATE SEQUENCE IF NOT EXISTS shop.roles_id_seq START WITH 1 increment by 1;
CREATE SEQUENCE IF NOT EXISTS shop.users_id_seq START WITH 1 increment by 1;

CREATE TABLE IF NOT EXISTS shop.product (
 id bigint NOT NULL DEFAULT nextval('shop.product_id_seq'::regclass),
 price integer NOT NULL,
 title VARCHAR (255) NOT NULL,
 CONSTRAINT product_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS shop.users (
 id bigint NOT NULL DEFAULT nextval('shop.users_id_seq'::regclass),
 enabled boolean NOT NULL DEFAULT true,
 username VARCHAR (255) NOT NULL,
 password VARCHAR (255) NOT NULL,
 CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS shop.roles (
 id bigint NOT NULL DEFAULT nextval('shop.roles_id_seq'::regclass),
 name VARCHAR (255) NOT NULL,
 CONSTRAINT roles_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS shop.user_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES shop.users (id),
    FOREIGN KEY (role_id) REFERENCES shop.roles (id),
    UNIQUE (user_id, role_id)
);


INSERT INTO shop.roles (id, name)
VALUES (DEFAULT, 'ROLE_USER'),
       (DEFAULT, 'ROLE_ADMIN');

INSERT INTO shop.users (id, enabled, password, username)
VALUES (DEFAULT, DEFAULT,'{noop}admin', 'admin'),
       (DEFAULT, DEFAULT,'{noop}user', 'user');

INSERT INTO shop.user_roles (user_id, role_id)
VALUES (1, 2),
       (2, 1);

INSERT INTO shop.product (id, price, title)
VALUES (DEFAULT, 10, 'Product1'),
       (DEFAULT, 20, 'Product2'),
       (DEFAULT, 30, 'Product3');

