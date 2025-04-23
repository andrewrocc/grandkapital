CREATE SCHEMA IF NOT EXISTS grand_kapital;

CREATE SEQUENCE IF NOT EXISTS users_id_sq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

CREATE SEQUENCE IF NOT EXISTS account_id_sq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

CREATE SEQUENCE IF NOT EXISTS email_data_id_sq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

CREATE SEQUENCE IF NOT EXISTS phone_data_id_sq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1
;

-- USERS
CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL,
    name VARCHAR(500) NOT NULL,
    date_of_birth DATE,
    password VARCHAR(500) NOT NULL
)
    WITH (autovacuum_enabled=true);

ALTER TABLE users ADD CONSTRAINT pk_users PRIMARY KEY (id);
ALTER TABLE users ADD CONSTRAINT chk_user_password_length CHECK (LENGTH(password) >= 8);

COMMENT ON TABLE users IS 'Таблица пользователей';
COMMENT ON COLUMN users.date_of_birth IS 'Формат даты: DD.MM.YYYY';
COMMENT ON COLUMN users.password IS 'Минимальная длина пароля: 8 символов, максимальная: 500';

-- ACCOUNT
CREATE TABLE IF NOT EXISTS account (
    id BIGINT NOT NULL,
    users_id BIGINT NOT NULL,
    balance DECIMAL(19, 2) NOT NULL
)
    WITH (autovacuum_enabled=true);

ALTER TABLE account ADD CONSTRAINT pk_account PRIMARY KEY (id);
ALTER TABLE account ADD CONSTRAINT fk_account_user FOREIGN KEY (users_id) REFERENCES users(id);

COMMENT ON TABLE account IS 'Таблица банковских счетов';
COMMENT ON COLUMN account.balance IS 'Баланс в рублях и копейках';

-- EMAIL_DATA
CREATE TABLE IF NOT EXISTS email_data (
    id BIGINT NOT NULL,
    users_id BIGINT,
    email VARCHAR(200) NOT NULL
)
    WITH (autovacuum_enabled=true);

ALTER TABLE email_data ADD CONSTRAINT pk_email_data PRIMARY KEY (id);
ALTER TABLE email_data ADD CONSTRAINT fk_email_data_user FOREIGN KEY (users_id) REFERENCES users(id);
ALTER TABLE email_data ADD CONSTRAINT uk_email_data_email UNIQUE (email);

COMMENT ON TABLE email_data IS 'Таблица email-адресов пользователей';

--  PHONE_DATA
CREATE TABLE IF NOT EXISTS phone_data (
    id BIGINT NOT NULL,
    users_id BIGINT,
    phone VARCHAR(13) NOT NULL
)
    WITH (autovacuum_enabled=true);

ALTER TABLE phone_data ADD CONSTRAINT pk_phone_data PRIMARY KEY (id);
ALTER TABLE phone_data ADD CONSTRAINT fk_phone_data_user FOREIGN KEY (users_id) REFERENCES users(id);
ALTER TABLE phone_data ADD CONSTRAINT uk_phone_data_phone UNIQUE (phone);
ALTER TABLE phone_data ADD CONSTRAINT chk_phone_data_phone_format CHECK (phone ~ '^7\d{10}$');

COMMENT ON TABLE phone_data IS 'Таблица телефонных номеров пользователей';
COMMENT ON COLUMN phone_data.phone IS 'Формат: 79207865432 (11 цифр, начинается с 7)';