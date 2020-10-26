DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2020-02-25 10:00', 'Завтрак', 700, 100000),
       ('2020-02-25 15:00', 'Обед', 1000, 100000),
       ('2020-02-25 18:00', 'Ужин', 1100, 100000),
       ('2020-02-26 10:00', 'Завтрак', 500, 100000),
       ('2020-02-25 13:00', 'Обед', 1200, 100000),
       ('2020-02-25 09:00', 'Завтрак', 900, 100001),
       ('2020-02-25 22:00', 'Ужин', 400, 100001),
       ('2020-02-26 10:00', 'Завтрак', 900, 100001);
