CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT       NOT NULL,
    role    VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, role),
    CONSTRAINT fk_users_roles_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS book
(
    id               BIGSERIAL NOT NULL,
    title            varchar(45) DEFAULT NULL,
    author           varchar(45) DEFAULT NULL,
    description      text        DEFAULT NULL,
    copies           int         DEFAULT NULL,
    copies_available int         DEFAULT NULL,
    category         varchar     DEFAULT NULL,
    img_data_id      int         DEFAULT NULL,
    imagedata_id     BIGINT      DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS review
(
    id                 BIGSERIAL NOT NULL,
    user_email         varchar(45)   DEFAULT NULL,
    date               date          DEFAULT NULL,
    rating             decimal(3, 2) DEFAULT NULL,
    book_id            BIGINT        DEFAULT NULL,
    review_description text          DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS checkout
(
    id            BIGSERIAL NOT NULL,
    user_email    varchar(45) DEFAULT NULL,
    checkout_date varchar(45) DEFAULT NULL,
    return_date   varchar(45) DEFAULT NULL,
    book_id       BIGINT      DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS messages
(
    id          BIGSERIAL NOT NULL,
    user_email  varchar(45) DEFAULT NULL,
    title       varchar(45) DEFAULT NULL,
    question    text        DEFAULT NULL,
    admin_email varchar(45) DEFAULT NULL,
    response    text        DEFAULT NULL,
    closed      smallint    DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS history
(
    id            BIGSERIAL NOT NULL,
    user_email    varchar(45) DEFAULT NULL,
    checkout_date varchar(45) DEFAULT NULL,
    returned_date varchar(45) DEFAULT NULL,
    title         varchar(45) DEFAULT NULL,
    author        varchar(45) DEFAULT NULL,
    description   text        DEFAULT NULL,
    img           BYTEA       DEFAULT NULL,
    PRIMARY KEY (id)
)