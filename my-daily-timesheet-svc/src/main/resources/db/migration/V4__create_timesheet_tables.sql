CREATE TABLE timesheet (
    id BIGSERIAL PRIMARY KEY,
    period_name VARCHAR(255),
    period_start_date DATE,
    period_end_date DATE,
    number_of_working_day INTEGER,
    created_datetime TIMESTAMP,
    updated_datetime TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE timesheet_approver (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    user_id BIGINT REFERENCES app_user(id),
    approver_label VARCHAR(255),
    sequence_order INTEGER,
    created_datetime TIMESTAMP,
    updated_datetime TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE timesheet_approval_history (
    id BIGSERIAL PRIMARY KEY,
    timesheet_id BIGINT NOT NULL REFERENCES timesheet(id),
    date DATE,
    status VARCHAR(50) NOT NULL,
    note TEXT,
    created_datetime TIMESTAMP,
    updated_datetime TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

CREATE TABLE timesheet_entry (
    id BIGSERIAL PRIMARY KEY,
    timesheet_id BIGINT NOT NULL REFERENCES timesheet(id),
    date DATE,
    vendor_name VARCHAR(255),
    user_id BIGINT REFERENCES app_user(id),
    spk VARCHAR(255),
    hour_start TIME,
    hour_end TIME,
    description TEXT,
    project_id BIGINT REFERENCES project(id),
    activity VARCHAR(255),
    working_type VARCHAR(50),
    created_datetime TIMESTAMP,
    updated_datetime TIMESTAMP,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);
