DROP TABLE IF EXISTS loans;
DROP TABLE IF EXISTS users;

CREATE TABLE users(
  user_id VARCHAR(64),
  name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  is_blacklist BOOLEAN NOT NULL,
  PRIMARY KEY (user_id)
);


CREATE TABLE loans(
  loan_id VARCHAR(64) DEFAULT RANDOM_UUID ( ),
  user_id VARCHAR(64),
  term INT NOT NULL,
  amount INT NOT NULL,
  country VARCHAR(2) NOT NULL,
  added TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (loan_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('ivan_petrov@test.ru', 'Иван', 'Петров', FALSE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('max_ivanov@test.ru', 'Максим', 'Иванов', FALSE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('irina_kuz@test.ru', 'Ирина', 'Кузнецова', FALSE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('kachalov@test.ru', 'Иларион ', 'Качалов', FALSE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('voronina@test.ru', 'Прасковья', 'Воронина', FALSE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('sokolova@test.ru', 'Татьяна', 'Соколова', FALSE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('emma@test.ru', 'Эмма', 'Андронова', FALSE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('adam@test.ru', 'Адам', 'Лютов', FALSE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('krilov_savel@test.ru', 'Савелий', 'Крылов', FALSE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('titov@test.ru', 'Глеб', 'Титов', FALSE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('fake1@test.ru', 'Сергей', 'Мавроди', TRUE);
INSERT INTO users(user_id, name, surname, is_blacklist) VALUES ('fake2@test.ru', 'Остап', 'Бендер', TRUE);


INSERT INTO loans(user_id, term, amount, country) VALUES ('max_ivanov@test.ru', 30, 1000, 'RU');
INSERT INTO loans(user_id, term, amount, country) VALUES ('irina_kuz@test.ru', 15, 200,  'LV');
INSERT INTO loans(user_id, term, amount, country) VALUES ('irina_kuz@test.ru', 30, 3000, 'LV');
INSERT INTO loans(user_id, term, amount, country) VALUES ('kachalov@test.ru', 14, 800,  'PL');
INSERT INTO loans(user_id, term, amount, country) VALUES ('voronina@test.ru', 10, 100,  'FI');
INSERT INTO loans(user_id, term, amount, country) VALUES ('voronina@test.ru', 30, 700,  'FI');
INSERT INTO loans(user_id, term, amount, country) VALUES ('emma@test.ru', 30, 900,  'FR');
INSERT INTO loans(user_id, term, amount, country) VALUES ('adam@test.ru', 14, 1200, 'US');
INSERT INTO loans(user_id, term, amount, country) VALUES ('adam@test.ru', 30, 2000, 'US');
INSERT INTO loans(user_id, term, amount, country) VALUES ('adam@test.ru', 60, 3500, 'US');
INSERT INTO loans(user_id, term, amount, country) VALUES ('krilov_savel@test.ru', 5,  600,  'UA');
