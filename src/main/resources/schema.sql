CREATE TABLE facility (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    capacity INT NOT NULL
);

CREATE TABLE room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(255) NOT NULL,  -- Changed "name" to "room_number" for consistency
    type VARCHAR(100) NOT NULL,         -- Added "type" to match the Java entity
    facility_id BIGINT NOT NULL,
    capacity INT NOT NULL,
    FOREIGN KEY (facility_id) REFERENCES facility(id) ON DELETE CASCADE
);

