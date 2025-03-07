# Facility and Insurance Claim Management API

## Overview
This API provides endpoints to manage patients and their insurance claims. It allows operations such as creating patients, retrieving patients by various filters, and managing insurance claims for patients.

## Base URL
```
http://localhost:8080
```

## Prerequisites

Install Redis from Microsoft Archive Redis Releases:
```
https://github.com/microsoftarchive/redis/releases
```

## Endpoints

### 1️⃣ Patient Endpoints

#### ➤ Create a Patient
**URL:** `/patients`  
**Method:** `POST`  
**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "1985-06-15",
  "gender": "Male",
  "contactNumber": "1234567890",
  "email": "john.doe@email.com",
  "address": "123 Main St, New York, NY"
}
```
**Response:**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "1985-06-15",
  "gender": "Male",
  "contactNumber": "1234567890",
  "email": "john.doe@email.com",
  "address": "123 Main St, New York, NY"
}
```

#### ➤ Get All Patients
**URL:** `/patients`  
**Method:** `GET`

#### ➤ Get Patient By ID
**URL:** `/patients/{id}`  
**Method:** `GET`  
**Example:** `/patients/1`

#### ➤ Update Patient
**URL:** `/patients/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "dateOfBirth": "1990-02-25",
  "gender": "Female",
  "contactNumber": "9876543210",
  "email": "jane.doe@email.com",
  "address": "456 Elm St, Los Angeles, CA"
}
```

#### ➤ Delete Patient
**URL:** `/patients/{id}`  
**Method:** `DELETE`

#### ➤ Clear Cache
**URL:** `/patients/clear-cache`  
**Method:** `GET`  
**Response:**
```
Cache Cleared
```

---

### 2️⃣ Insurance Claim Endpoints

#### ➤ Create an Insurance Claim
**URL:** `/claims`  
**Method:** `POST`  
**Request Body:**
```json
{
  "claimType": "Medical",
  "amount": 2500.50,
  "status": "Approved",
  "claimDate": "2024-01-15",
  "patientId": 1
}
```

#### ➤ Get All Claims
**URL:** `/claims`  
**Method:** `GET`

#### ➤ Get Claim By ID
**URL:** `/claims/{id}`  
**Method:** `GET`  
**Example:** `/claims/1`

#### ➤ Get Claims By Patient ID
**URL:** `/claims/patient/{patientId}`  
**Method:** `GET`  
**Example:** `/claims/patient/1`

#### ➤ Update Claim
**URL:** `/claims/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "claimType": "Dental",
  "amount": 1800.75,
  "status": "Pending",
  "claimDate": "2024-02-10",
  "patientId": 2
}
```

#### ➤ Delete Claim
**URL:** `/claims/{id}`  
**Method:** `DELETE`

---

## Sample SQL Data

### ✅ Insert 10 Patients
```sql
INSERT INTO patient (first_name, last_name, date_of_birth, gender, contact_number, email, address) VALUES
('John', 'Doe', '1985-06-15', 'Male', '1234567890', 'john.doe@email.com', '123 Main St, New York, NY'),
('Jane', 'Smith', '1990-02-25', 'Female', '9876543210', 'jane.smith@email.com', '456 Elm St, Los Angeles, CA'),
('Michael', 'Johnson', '1978-11-03', 'Male', '1122334455', 'michael.j@email.com', '789 Pine St, Chicago, IL');
```

### ✅ Insert 10 Insurance Claims
```sql
INSERT INTO insurance_claim (claim_type, amount, status, claim_date, patient_id) VALUES
('Medical', 2500.50, 'Approved', '2024-01-15', 1),
('Dental', 1800.75, 'Pending', '2024-02-10', 2),
('Surgery', 5000.00, 'Rejected', '2023-12-05', 3);
```

## 📌 Notes
- Ensure your database is set up before using the API.
- Replace `{id}` and `{patientId}` with actual values when making requests.
- JSON request bodies must be correctly formatted.
- Cache can be cleared manually using `/patients/clear-cache` endpoint.

