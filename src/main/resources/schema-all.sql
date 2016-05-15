DROP TABLE customer IF EXISTS;

CREATE TABLE customer (
        customer_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
        title VARCHAR(5),
        first_name VARCHAR(50),
        last_name VARCHAR(50),
        email VARCHAR(100),
        dob DATETIME,
        home_phone VARCHAR(12),
        mobile_phone VARCHAR(12),
        employment_status VARCHAR(20),
        employment_start_date DATETIME,
        salary_amount FLOAT
);

DROP TABLE address IF EXISTS;

CREATE TABLE address (
    address_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    address_line_1 VARCHAR(100),
    address_line_2 VARCHAR(100),
    address_line_3 VARCHAR(100),
    town VARCHAR(50),
    county VARCHAR(50),
    post_code VARCHAR(10)
);

DROP TABLE account IF EXISTS;

CREATE TABLE account (
    account_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    account_name VARCHAR(50),
    api_key VARCHAR(36)
);

INSERT INTO account (account_name, api_key) VALUES ('Test Account', 'xxxx-xxxx-xxxx-xxxx');

DROP TABLE cascade IF EXISTS;

CREATE TABLE cascade (
    cascade_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    cascade_name VARCHAR(100),
    cascade_object VARCHAR(100)
);

INSERT INTO cascade (cascade_name, cascade_object)
VALUES
    ('Cascade One', 'PingOne'),
    ('Cascade Two', 'PingTwo');

DROP TABLE account_cascade_order IF EXISTS;

CREATE TABLE account_cascade_order (
    account_id BIGINT NOT NULL,
    cascade_id BIGINT NOT NULL,
    cascade_order INT NOT NULL
);

INSERT INTO account_cascade_order (account_id, cascade_id, cascade_order)
VALUES
    (1, 1, 1),
    (1, 2, 2);

