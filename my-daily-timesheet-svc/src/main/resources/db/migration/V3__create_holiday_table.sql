CREATE TABLE holiday (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    holiday_name VARCHAR(255) NOT NULL,
    created_datetime TIMESTAMP,
    updated_datetime TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);
