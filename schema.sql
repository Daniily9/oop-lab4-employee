CREATE TABLE employees (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(50) NOT NULL,
    salary DOUBLE PRECISION NOT NULL,
    experience_years INTEGER NOT NULL,
    email VARCHAR(255) NOT NULL,
    contract_months INTEGER,
    bonus DOUBLE PRECISION,
    weekly_hours INTEGER,
    educational_institution VARCHAR(255),
    internship_months INTEGER
);