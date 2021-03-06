DROP TABLE IF EXISTS USER_ROLES;
DROP TABLE IF EXISTS VOTES;
--DROP TABLE IF EXISTS MENUS;
DROP TABLE IF EXISTS MENU_DETAILS;
DROP TABLE IF EXISTS RESTAURANT_OWNER;
DROP TABLE IF EXISTS RESTAURANTS;
DROP TABLE IF EXISTS USERS;
DROP SEQUENCE IF EXISTS GLOBAL_SEQ;
DROP SEQUENCE IF EXISTS EXTRA_SEQ;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;
CREATE SEQUENCE EXTRA_SEQ AS INTEGER START WITH 100;

CREATE TABLE USERS
(
    id         INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOLEAN   DEFAULT TRUE  NOT NULL
);

CREATE UNIQUE INDEX users_unique_email_idx
    ON USERS (email);

CREATE TABLE USER_ROLES
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

create table RESTAURANTS
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    manager_id  integer                 not null,
    name        varchar(255)            not null,
    address     varchar(255)            NOT NULL,
    owner       varchar(100),
    registered  TIMESTAMP DEFAULT now() NOT NULL,
    description varchar(255),
    FOREIGN KEY (manager_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX restaurant_unique_name_user_idx
    ON RESTAURANTS (name, address);


create table menu_details
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE EXTRA_SEQ PRIMARY KEY,
    restaurant_id integer                      not null, -- ресторан
    type_dish     varchar(100),                          -- тип блюда, типа горячие закуски, холодные закуски, салаты и тюд. Вынести потом в отдельную таблицу
    description   varchar(1000)                not null, -- шашлык из свининых ребрышек
    quantity      varchar(100),                          -- количество - 100 гр
    price         DECIMAL(10, 2) DEFAULT 0.00,           -- цена
    date_time     TIMESTAMP      DEFAULT now() NOT NULL, -- последняя дата редактирования
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE cascade
);

-- CREATE TABLE menus
-- (
--     id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
--     menus_details_id integer           not null,
--     description      varchar(1000),
--     amount           DECIMAL(10, 2),
--     discount         integer DEFAULT 0 not null,
--     date_time        TIMESTAMP   DEFAULT now()      NOT NULL,
--     FOREIGN KEY (menus_details_id) REFERENCES menu_details (id) ON DELETE cascade
--
-- );
-- CREATE UNIQUE INDEX menu_unique_name_user_idx
--     ON menus (menus_details_id, date_time);


CREATE TABLE votes
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    user_id       INTEGER                 NOT NULL,
    restaurant_id integer                 not null,
    date_time     TIMESTAMP DEFAULT now() NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX rating_unique_user_datetime_idx
    ON votes (user_id, date_time);