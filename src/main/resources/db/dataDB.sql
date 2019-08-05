DELETE FROM MENUS;
DELETE FROM RESTORANS;
DELETE FROM USER_ROLES;
DELETE FROM USERS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin'),
  ('Manager', 'manager@yandex.ru', 'manager');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'User')),
  ('ROLE_ADMIN', (select U.ID FROM USERS U WHERE U.NAME = 'Admin')),
  ('ROLE_ADMIN', (select U.ID FROM USERS U WHERE U.NAME = 'Manager') ),
  ('ROLE_USER', (select U.ID FROM USERS U WHERE U.NAME = 'Admin'));
--
INSERT INTO RESTORANS (NAME, USER_ID)  VALUES
  ('Star',(select U.ID FROM USERS U WHERE U.NAME = 'Admin') ),
  ('Pearl',(select  U.ID FROM USERS U WHERE U.NAME = 'Manager'));

INSERT INTO MENUS (RESTORAN_ID, DESCRIPTION, DATE_TIME) VALUES
    ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Star'),'meal_1',curdate()),
    ((SELECT R.ID FROM RESTORANS R WHERE R.NAME = 'Pearl'),'meal_2',curdate());