DELETE
FROM MENUS;
DELETE
FROM RESTORANS;
DELETE
FROM USER_ROLES;
DELETE
FROM USERS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),           --100000
       ('Admin', 'admin@gmail.com', 'admin'),            --100001
       ('Manager', 'manager@yandex.ru', 'manager'),      --100002
       ('User_1', 'user_1@yandex.ru', 'password1'),      --100003
       ('User_2', 'user_2@yandex.ru', 'password2'),      --100004
       ('User_3', 'user_3@yandex.ru', 'password3'),      --100005
       ('User_4', 'user_4@yandex.ru', 'password4'),      --100006
       ('User_5', 'user_5@yandex.ru', 'password5'),      --100007
       ('User_6', 'user_6@yandex.ru', 'password6'),      --100008
       ('User_7', 'user_7@yandex.ru', 'password7'),      --100009
       ('User_8', 'user_8@yandex.ru', 'password8'),      --100010
       ('User_9', 'user_9@yandex.ru', 'password9'),      --100011
       ('Manager_1', 'manager_1@yandex.ru', '1password'),--100012
       ('Manager_2', 'manager_2@yandex.ru', '2password'),--100013
       ('Manager_3', 'manager_3@yandex.ru', '3password');--100014

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User')),
       ('ROLE_ADMIN', (select U.ID FROM USERS U WHERE U.NAME = 'Admin')),
       ('ROLE_ADMIN', (select U.ID FROM USERS U WHERE U.NAME = 'Manager')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'Admin')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_1')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_2')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_3')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_4')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_5')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_6')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_7')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_8')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_9')),
       ('ROLE_ADMIN', (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1')),
       ('ROLE_ADMIN', (select U.ID FROM USERS U WHERE U.NAME = 'Manager_2')),
       ('ROLE_ADMIN', (select U.ID FROM USERS U WHERE U.NAME = 'Manager_3'))
;
--
INSERT INTO RESTORANS (NAME, USER_ID)
VALUES ('Star', (select U.ID FROM USERS U WHERE U.NAME = 'Admin')),       --100015
       ('Pearl', (select U.ID FROM USERS U WHERE U.NAME = 'Manager')),    --100016
       ('Star_1', (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1')), --100017
       ('Star_2', (select U.ID FROM USERS U WHERE U.NAME = 'Manager_2')), --100018
       ('Star_3', (select U.ID FROM USERS U WHERE U.NAME = 'Manager_3')); --100019
;

INSERT INTO MENUS (RESTORAN_ID, DESCRIPTION, DATE_TIME)
VALUES ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), 'Star_meal_dinner', curdate()), --100020
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'), 'Pearl_meal_dinner', curdate()), --100021
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_1'), 'Star_1_meal_dinner', curdate()), --100022
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_2'), 'Star_2_meal_dinner', curdate()), --100023
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_3'), 'Star_3_meal_dinner', curdate()); --100024
