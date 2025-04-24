DELETE FROM account;

ALTER SEQUENCE account_id_sq RESTART WITH 1;

ALTER TABLE account ADD COLUMN init_deposit NUMERIC(19, 2) NOT NULL;

INSERT INTO account (id, users_id, balance, init_deposit) VALUES
(1, 1, 150000.50, 100000.00),  -- init_deposit меньше текущего баланса
(2, 2, 275000.00, 300000.00),   -- init_deposit больше текущего баланса
(3, 3, 50000.75, 50000.75),     -- равные значения
(4, 4, 1200000.25, 1000000.00),
(5, 5, 85000.00, 100000.00);