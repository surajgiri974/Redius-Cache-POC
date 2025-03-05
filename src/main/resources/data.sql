-- ✅ Insert 10 Facilities
INSERT INTO facility (name, location, type, capacity) VALUES ('City Hospital', 'New York', 'Hospital', 500);
INSERT INTO facility (name, location, type, capacity) VALUES ('Sunrise Clinic', 'Los Angeles', 'Clinic', 200);
INSERT INTO facility (name, location, type, capacity) VALUES ('Metro Healthcare', 'Chicago', 'Hospital', 800);
INSERT INTO facility (name, location, type, capacity) VALUES ('Wellness Center', 'Houston', 'Wellness', 300);
INSERT INTO facility (name, location, type, capacity) VALUES ('Greenfield Hospital', 'Boston', 'Hospital', 400);
INSERT INTO facility (name, location, type, capacity) VALUES ('Oceanview Clinic', 'San Francisco', 'Clinic', 150);
INSERT INTO facility (name, location, type, capacity) VALUES ('Pinecrest Hospital', 'Seattle', 'Hospital', 600);
INSERT INTO facility (name, location, type, capacity) VALUES ('Riverside Medical', 'Denver', 'Medical Center', 350);
INSERT INTO facility (name, location, type, capacity) VALUES ('Grandview Healthcare', 'Atlanta', 'Hospital', 700);
INSERT INTO facility (name, location, type, capacity) VALUES ('Silverline Clinic', 'Dallas', 'Clinic', 180);

-- ✅ Insert 10 Rooms (Assign them to Facilities)
INSERT INTO room (room_number, type, capacity, facility_id) VALUES ('101', 'ICU', 2, 1);
INSERT INTO room (room_number, type, capacity, facility_id) VALUES ('102', 'General Ward', 4, 2);
INSERT INTO room (room_number, type, capacity, facility_id) VALUES ('201', 'Surgery', 1, 3);
INSERT INTO room (room_number, type, capacity, facility_id) VALUES ('202', 'Maternity', 2, 4);
INSERT INTO room (room_number, type, capacity, facility_id) VALUES ('301', 'ICU', 3, 5);
INSERT INTO room (room_number, type, capacity, facility_id) VALUES ('302', 'General Ward', 5, 6);
INSERT INTO room (room_number, type, capacity, facility_id) VALUES ('401', 'Pediatrics', 2, 7);
INSERT INTO room (room_number, type, capacity, facility_id) VALUES ('402', 'Emergency', 3, 8);
INSERT INTO room (room_number, type, capacity, facility_id) VALUES ('501', 'Maternity', 2, 9);
INSERT INTO room (room_number, type, capacity, facility_id) VALUES ('502', 'ICU', 1, 10);
