/*CREATE TABLE users
(
    id       SERIAL       NOT NULL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE roles
(
    id   SERIAL       NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) references users (id),
    FOREIGN KEY (role_id) references roles (id),
    UNIQUE (user_id, role_id)
);

INSERT INTO users VALUES (1, 'name_user1', 'lasdjlasdjf');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1,2);

*/