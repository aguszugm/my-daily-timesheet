CREATE TABLE app_user (
    id BIGINT PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    signature_image BYTEA,
    is_admin BOOLEAN NOT NULL DEFAULT FALSE,
    created_datetime TIMESTAMP,
    updated_datetime TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE SEQUENCE app_user_seq START WITH 1 INCREMENT BY 50;
