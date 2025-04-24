INSERT INTO users (id, name, date_of_birth, password) VALUES
(nextval('users_id_sq'), 'Иван Петров', '1990-05-15', 'SecurePass123'),
(nextval('users_id_sq'), 'Мария Сидорова', '1985-11-22', 'StrongPassword456'),
(nextval('users_id_sq'), 'Алексей Иванов', '1995-03-08', 'SafePass789'),
(nextval('users_id_sq'), 'Елена Кузнецова', '1988-07-30', 'ProtectedPass012'),
(nextval('users_id_sq'), 'Дмитрий Смирнов', '1992-09-14', 'SecretPass345');

-- Вставка аккаунтов (строго по одному на пользователя)
INSERT INTO account (id, users_id, balance) VALUES
(nextval('account_id_sq'), 1, 150000.50),
(nextval('account_id_sq'), 2, 275000.00),
(nextval('account_id_sq'), 3, 50000.75),
(nextval('account_id_sq'), 4, 1200000.25),
(nextval('account_id_sq'), 5, 85000.00);

-- Вставка email'ов (минимум 1)
INSERT INTO email_data (id, users_id, email) VALUES
 -- Основные email (по одному обязательному на пользователя)
 (nextval('email_data_id_sq'), 1, 'ivan.petrov@example.com'),
 (nextval('email_data_id_sq'), 2, 'maria.sidorova@example.com'),
 (nextval('email_data_id_sq'), 3, 'alexey.ivanov@example.com'),
 (nextval('email_data_id_sq'), 4, 'elena.kuznetsova@example.com'),
 (nextval('email_data_id_sq'), 5, 'dmitry.smirnov@example.com'),

 -- Дополнительные email (необязательные)
 (nextval('email_data_id_sq'), 1, 'ivan.petrov.work@example.com'),
 (nextval('email_data_id_sq'), 3, 'alexey.ivanov.personal@example.com'),
 (nextval('email_data_id_sq'), 4, 'elena.k.1988@example.com');

-- Вставка телефонов (минимум 1)
INSERT INTO phone_data (id, users_id, phone) VALUES
 -- Основные телефоны (по одному обязательному на пользователя)
 (nextval('phone_data_id_sq'), 1, '79151234567'),
 (nextval('phone_data_id_sq'), 2, '79262345678'),
 (nextval('phone_data_id_sq'), 3, '79373456789'),
 (nextval('phone_data_id_sq'), 4, '79484567890'),
 (nextval('phone_data_id_sq'), 5, '79595678901'),

 -- Дополнительные телефоны (необязательные)
 (nextval('phone_data_id_sq'), 1, '79157654321'),
 (nextval('phone_data_id_sq'), 3, '79379876543'),
 (nextval('phone_data_id_sq'), 5, '79593216547');
