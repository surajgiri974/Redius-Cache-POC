CREATE TABLE patient (
    patient_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    contact_number VARCHAR(15),
    email VARCHAR(255) UNIQUE,
    address TEXT
);

CREATE TABLE insurance_claim (
    claim_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    claim_type VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL,
    claim_date DATE NOT NULL,
    patient_id BIGINT NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE
);
