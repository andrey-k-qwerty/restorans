DROP TABLE IF EXISTS USER_ROLES;
DROP TABLE IF EXISTS RAITING;
DROP TABLE IF EXISTS MENUS;
DROP TABLE IF EXISTS MENU_DETAILS;
DROP TABLE IF EXISTS RESTORANS;
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
    -- calories_per_day INTEGER DEFAULT 2000    NOT NULL
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
-- изменить потом юзерайди на манаджерайди
create table RESTORANS
(
    id      INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name    varchar(255) not null,
    user_id INTEGER      NOT NULL,
  --  address varchar(255)
    FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE UNIQUE INDEX restaurant_unique_name_user_idx
    ON RESTORANS (name, user_id);

create table menu_details
(
    id          INTEGER GENERATED BY DEFAULT AS SEQUENCE EXTRA_SEQ PRIMARY KEY,
    restoran_id integer   not null,
    manager_id  integer   not null,
    description varchar(1000),
    date_time   TIMESTAMP NOT NULL,
    FOREIGN KEY (restoran_id) REFERENCES restorans (id) ON DELETE cascade,
    FOREIGN KEY (manager_id) REFERENCES users (id) ON DELETE cascade
);

create table menus
(
    id               INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    menus_details_id integer   not null,
    manager_id       integer   not null,
    description      varchar(1000),
    price            DECIMAL(5, 2),
    discount         integer   DEFAULT 0 not null ,
    date_time        TIMESTAMP NOT NULL,
    FOREIGN KEY (menus_details_id) REFERENCES menu_details (id) ON DELETE cascade,
    FOREIGN KEY (manager_id) REFERENCES users (id)
);
-- CREATE UNIQUE INDEX menu_unique_name_user_idx
--     ON menus (menus_details_id, date_time);



CREATE TABLE raiting
(
    id        INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    user_id   INTEGER   NOT NULL,
    menu_id   integer   not null,
    date_time TIMESTAMP NOT NULL,
    --  description TEXT                             NOT NULL,
    --   enabled     BOOL                DEFAULT TRUE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX raiting_unique_user_datetime_idx
    ON raiting (user_id, date_time);