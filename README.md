# Facility and Room Management API

## Overview
This API provides endpoints to manage facilities and rooms within those facilities. It allows operations such as creating facilities, retrieving facilities by various filters, and managing rooms within a facility.

## Base URL
```
http://localhost:8080
```

## Prerequisites

Install Redis from Microsoft Archive Redis Releases
```
https://github.com/microsoftarchive/redis/releases
```

## Endpoints

### 1️⃣ Facility Endpoints

#### ➤ Create a Facility
**URL:** `/facilities`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "Sunrise Clinic",
  "location": "Los Angeles",
  "type": "Clinic",
  "capacity": 200
}
```
**Response:**
```json
{
  "id": 2,
  "name": "Sunrise Clinic",
  "location": "Los Angeles",
  "type": "Clinic",
  "capacity": 200
}
```

#### ➤ Get All Facilities
**URL:** `/facilities`  
**Method:** `GET`

#### ➤ Get Facility By ID
**URL:** `/facilities/{id}`  
**Method:** `GET`
**Example:** `/facilities/1`

#### ➤ Get Facilities By Type
**URL:** `/facilities/type/{type}`  
**Method:** `GET`
**Example:** `/facilities/type/Hospital`

#### ➤ Get Facilities By Location
**URL:** `/facilities/location/{location}`  
**Method:** `GET`
**Example:** `/facilities/location/New York`

#### ➤ Filter Facilities
**URL:** `/facilities/filter`  
**Method:** `POST`  
**Request Body:**
```json
{
  "location": "",
  "type": "Hospital"
}
```

#### ➤  Update Facility by Name
**URL:** `/facilities/update/{name}`  
**Method:** `PUT`
**Example:** `/facilities/update/Sunrise Hospital`

#### ➤ Get Rooms By Facility
**URL:** `/facilities/{facilityId}/rooms`  
**Method:** `GET`
**Example:** `/facilities/1/rooms`



#### ➤ Add a Room to Facility
**URL:** `/facilities/{facilityId}/rooms`  
**Method:** `POST`  
**Request Body:**
```json
{
  "roomNumber": "601",
  "type": "General Ward",
  "capacity": 4
}
```

### 2️⃣ Room Endpoints

#### ➤ Get All Rooms
**URL:** `/rooms`  
**Method:** `GET`

#### ➤ Get Rooms By Type
**URL:** `/rooms/type/{type}`  
**Method:** `GET`
**Example:** `/rooms/type/ICU`

#### ➤ Get Rooms By Facility
**URL:** `/rooms/facility/{facilityId}`  
**Method:** `GET`
**Example:** `/rooms/facility/1`

#### ➤ Create a Room
**URL:** `/rooms`  
**Method:** `POST`  
**Request Body:**
```json
{
  "roomNumber": "701",
  "type": "ICU",
  "capacity": 3,
  "facilityId": 1
}
```

## Sample SQL Data

### ✅ Insert 10 Facilities
```sql
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
```

### ✅ Insert 10 Rooms (Assign them to Facilities)
```sql
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
```

## 📌 Notes
- Ensure your database is set up before using the API.
- Replace `{id}` and `{facilityId}` with actual values when making requests.
- JSON request bodies must be correctly formatted.
- For filtering, if a value is empty, the API should return results based on available parameters.

