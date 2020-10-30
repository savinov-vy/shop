DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS product_id_seq;
DROP SEQUENCE IF EXISTS roles_id_seq;
DROP SEQUENCE IF EXISTS users_id_seq;

CREATE SEQUENCE IF NOT EXISTS product_id_seq START WITH 1 increment by 1;
CREATE SEQUENCE IF NOT EXISTS roles_id_seq START WITH 1 increment by 1;
CREATE SEQUENCE IF NOT EXISTS users_id_seq START WITH 1 increment by 1;

CREATE SEQUENCE IF NOT EXISTS objects_id_seq ;

CREATE TABLE IF NOT EXISTS postgres.public.product (
 id bigint NOT NULL DEFAULT nextval('product_id_seq'::regclass),
 price integer NOT NULL,
 title VARCHAR (255) NOT NULL,
 CONSTRAINT product_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS postgres.public.users (
 id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
 enabled boolean NOT NULL DEFAULT true,
 username VARCHAR (255) NOT NULL,
 password VARCHAR (255) NOT NULL,
 CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS postgres.public.roles (
 id bigint NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
 name VARCHAR (255) NOT NULL,
 CONSTRAINT roles_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS postgres.public.user_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    UNIQUE (user_id, role_id)
);


INSERT INTO roles (id, name)
VALUES (DEFAULT, 'ROLE_USER'),
       (DEFAULT, 'ROLE_ADMIN');

INSERT INTO users (id, enabled, password, username)
VALUES (DEFAULT, DEFAULT,'{noop}admin', 'admin'),
       (DEFAULT, DEFAULT,'{noop}user', 'user');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 2),
       (2, 1);

INSERT INTO product (id, price, title)
VALUES (DEFAULT, 10, 'Product1'),
       (DEFAULT, 20, 'Product2'),
       (DEFAULT, 30, 'Product3');

