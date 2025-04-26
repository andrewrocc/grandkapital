INSERT INTO users (id, name, date_of_birth, password) VALUES
(1, 'Иван Петров', '1990-05-15', '$2a$10$8LMwQJbP0zR6c.y/RD/ErOwHqPLH2AU.2GwBCg0C3qKBMPWbJgBQm'),
(2, 'Мария Сидорова', '1985-11-22', '$2a$10$6iA/yn/TjHx8nmBi7bmKF.8Zszc5rcPX/uzmsOhG3psbu/SRcyffK'),
(3, 'Алексей Иванов', '1995-03-08', '$2a$10$jLbZoTFDijBbHRiHyXxom.HqNBQxpjaXeokG87w0pUEn2KT35FnZC'),
(4, 'Елена Кузнецова', '1988-07-30', '$2a$10$U5wtL468PcEz148jVNeO3uqyHbAPstAY2Z.IHDiv/.LQWbeMdEVIe'),
(5, 'Дмитрий Смирнов', '1992-09-14', '$2a$10$8BsmIbP/S2XYZn0Eh2WpxeM40BVAJj.MTbX9jH/sZMKa7MdsJEbYO');

INSERT INTO account (id, users_id, balance, init_deposit) VALUES
(1, 1, 150000.50, 100000.00),  -- init_deposit меньше текущего баланса
(2, 2, 275000.00, 300000.00),   -- init_deposit больше текущего баланса
(3, 3, 50000.75, 50000.75),     -- равные значения
(4, 4, 1200000.25, 1000000.00),
(5, 5, 85000.00, 100000.00);

INSERT INTO email_data (id, users_id, email) VALUES
 -- Основные email (по одному обязательному на пользователя)
 (1, 1, 'ivan.petrov@example.com'),
 (2, 2, 'maria.sidorova@example.com'),
 (3, 3, 'alexey.ivanov@example.com'),
 (4, 4, 'elena.kuznetsova@example.com'),
 (5, 5, 'dmitry.smirnov@example.com'),

 -- Дополнительные email (необязательные)
 (6, 1, 'ivan.petrov.work@example.com'),
 (7, 3, 'alexey.ivanov.personal@example.com'),
 (8, 4, 'elena.k.1988@example.com');

INSERT INTO phone_data (id, users_id, phone) VALUES
 -- Основные телефоны (по одному обязательному на пользователя)
 (1, 1, '79151234567'),
 (2, 2, '79262345678'),
 (3, 3, '79373456789'),
 (4, 4, '79484567890'),
 (5, 5, '79595678901'),

 -- Дополнительные телефоны (необязательные)
 (6, 1, '79157654321'),
 (7, 3, '79379876543'),
 (8, 5, '79593216547');