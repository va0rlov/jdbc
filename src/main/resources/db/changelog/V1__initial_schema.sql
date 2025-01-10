-- Создание таблицы Account
CREATE TABLE account (
    id BIGINT PRIMARY KEY,
    amount BIGINT NOT NULL
);

-- Создание таблицы Contact
CREATE TABLE contact (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(255)
);