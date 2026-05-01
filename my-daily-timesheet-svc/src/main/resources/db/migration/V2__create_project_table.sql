CREATE TABLE project (
    id BIGSERIAL PRIMARY KEY,
    project_business_id VARCHAR(255),
    project_name VARCHAR(255) NOT NULL,
    created_datetime TIMESTAMP,
    updated_datetime TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);
