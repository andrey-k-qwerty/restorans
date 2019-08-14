DELETE
FROM MENUS;
DELETE
FROM RESTORANS;
DELETE
FROM USER_ROLES;
DELETE
FROM USERS;
DELETE
FROM MENU_DETAILS;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE extra_seq RESTART WITH 100;
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),      --100000
       ('Admin', 'admin@gmail.com', 'admin'),       --100001
       ('Manager', 'manager@yandex.ru', 'manager'), --100002
       ('User_1', 'user_1@yandex.ru', 'password1'), --100003
       ('User_2', 'user_2@yandex.ru', 'password2'), --100004
       ('User_3', 'user_3@yandex.ru', 'password3'), --100005
       ('Manager_1', 'manager_1@yandex.ru', '1password');--100006


INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User')),
       ('ROLE_ADMIN', (select U.ID FROM USERS U WHERE U.NAME = 'Admin')),
       ('ROLE_ADMIN', (select U.ID FROM USERS U WHERE U.NAME = 'Manager')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'Admin')), -- у админа две роли,думаю не надо или как минимум он не можнт голосовать за свой ресторан
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_1')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_2')),
       ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User_3')),
       ('ROLE_ADMIN', (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1'))
;
--
INSERT INTO RESTORANS (NAME, USER_ID)
VALUES ('Star', (select U.ID FROM USERS U WHERE U.NAME = 'Admin')),            --100007
       ('Pearl', (select U.ID FROM USERS U WHERE U.NAME = 'Manager')),         --100008
       ('Super Star', (select U.ID FROM USERS U WHERE U.NAME = 'Admin')),      --100009 , у Админа два ресторана (Star и 'Super Star')
       ('Black Pearl', (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1')), --100010 ,
       --100011, у ресторана 'Black Pearl' два админа (Manager_1, Manager) т.е у Manager тоже два ресторана
       ('Black Pearl', (select U.ID FROM USERS U WHERE U.NAME = 'Manager'));



-- INSERT INTO MENUS (RESTORAN_ID, DESCRIPTION, DATE_TIME)
-- VALUES ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), 'Star_meal_dinner', CURRENT_DATE),             --100020
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'), 'Pearl_meal_dinner', CURRENT_DATE),           --100021
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_1'), 'Star_1_meal_dinner', CURRENT_DATE),         --100022
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_2'), 'Star_2_meal_dinner', CURRENT_DATE),         --100023
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_3'), 'Star_3_meal_dinner', CURRENT_DATE),         --100024
--
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), 'Star_meal_lunch', CURRENT_DATE - 1 day),     --100025
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'), 'Pearl_meal_lunch', CURRENT_DATE - 1 day),   --100026
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_1'), 'Star_1_meal_lunch', CURRENT_DATE - 1 day), --100027
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_2'), 'Star_2_meal_lunch', CURRENT_DATE - 1 day), --100028
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_3'), 'Star_3_meal_lunch', CURRENT_DATE - 1 day), --100029
--
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), 'Star_meal_breakfast', CURRENT_DATE - 2 day),     --100030
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'), 'Pearl_meal_breakfast', CURRENT_DATE - 2 day),   --100031
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_1'), 'Star_1_meal_breakfast', CURRENT_DATE - 2 day), --100032
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_2'), 'Star_2_meal_breakfast', CURRENT_DATE - 2 day), --100033
--        ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star_3'), 'Star_3_meal_breakfast', CURRENT_DATE - 2 day); --100034

INSERT INTO MENU_DETAILS(RESTORAN_ID, MANAGER_ID, DESCRIPTION, DATE_TIME)

VALUES --Pearl-Admin
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'), (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '1. meal dish first', CURRENT_DATE),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'), (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '2. meal dish second', CURRENT_DATE),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'), (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '3. meal dish third', CURRENT_DATE),
       --Star-Manager
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), (select U.ID FROM USERS U WHERE U.NAME = 'Manager'),
        '1. meal dish first', CURRENT_DATE),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), (select U.ID FROM USERS U WHERE U.NAME = 'Manager'),
        '2. meal dish second', CURRENT_DATE),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), (select U.ID FROM USERS U WHERE U.NAME = 'Manager'),
        '3. meal dish third', CURRENT_DATE),
       -- 'Black Pearl' - Manager_1 = 100006 !!!!!!!!!!!!!!!!!!!!!!!! ДВА ХОЗЯИНА !!!!!!!!!!!!!!!!
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Black Pearl' and R.USER_ID =  (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1')),
        (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1'),
        '1. meal dish first', CURRENT_DATE),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Black Pearl' and R.USER_ID =  (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1')),
        (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1'),
        '2. meal dish second', CURRENT_DATE),
       -- 'Super Star' - Manager_1
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Super Star'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '1. meal dish first', CURRENT_DATE),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Super Star'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '2. meal dish second', CURRENT_DATE),--,

       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '1-1. meal dish first', CURRENT_DATE - 1 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '2-1. meal dish second', CURRENT_DATE - 1 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '3-1. meal dish third', CURRENT_DATE - 1 day),
       --Star-Manager
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), (select U.ID FROM USERS U WHERE U.NAME = 'Manager'),
        '1-1. meal dish first', CURRENT_DATE - 1 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), (select U.ID FROM USERS U WHERE U.NAME = 'Manager'),
        '2-1. meal dish second', CURRENT_DATE - 1 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), (select U.ID FROM USERS U WHERE U.NAME = 'Manager'),
        '3-1. meal dish third', CURRENT_DATE - 1 day),
       -- 'Black Pearl' - Manager_1 !!! ДВА ХОЗЯИНА !!!
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Black Pearl' and R.USER_ID =  (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1')),
        (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1'),
        '1-1. meal dish first', CURRENT_DATE - 1 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Black Pearl' and R.USER_ID =  (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1')),
        (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1'),
        '2-1. meal dish second', CURRENT_DATE - 1 day),
       -- 'Super Star' - Manager_1
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Super Star'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '1-1. meal dish first', CURRENT_DATE - 1 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Super Star'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '2-1. meal dish second', CURRENT_DATE - 1 day),


       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '1-1. meal dish first', CURRENT_DATE - 2 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '2-1. meal dish second', CURRENT_DATE - 2 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '3-1. meal dish third', CURRENT_DATE - 2 day),
       --Star-Manager
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), (select U.ID FROM USERS U WHERE U.NAME = 'Manager'),
        '1-1. meal dish first', CURRENT_DATE - 2 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), (select U.ID FROM USERS U WHERE U.NAME = 'Manager'),
        '2-1. meal dish second', CURRENT_DATE - 2 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'), (select U.ID FROM USERS U WHERE U.NAME = 'Manager'),
        '3-1. meal dish third', CURRENT_DATE - 2 day),
       -- 'Black Pearl' - Manager_1 !!! ДВА ХОЗЯИНА !!!
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Black Pearl' and R.USER_ID =  (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1')),
        (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1'),
        '1-1. meal dish first', CURRENT_DATE - 2 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Black Pearl' and R.USER_ID =  (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1')),
        (select U.ID FROM USERS U WHERE U.NAME = 'Manager_1'),
        '2-1. meal dish second', CURRENT_DATE - 2 day),
       -- 'Super Star' - Manager_1
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Super Star'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '1-1. meal dish first', CURRENT_DATE - 2 day),
       ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Super Star'),
        (select U.ID FROM USERS U WHERE U.NAME = 'Admin'),
        '2-1. meal dish second', CURRENT_DATE - 2 day);

