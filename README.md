# 🏨 Room Service API Automation Suite

![Java](https://img.shields.io/badge/Java-8-blue)
![TestNG](https://img.shields.io/badge/TestNG-Framework-brightgreen)
![RestAssured](https://img.shields.io/badge/Rest--Assured-API%20Testing-orange)
![ExtentReports](https://img.shields.io/badge/Reporting-ExtentReports-yellow)
![Maven](https://img.shields.io/badge/Build-Maven-purple)

---

## 📌 Overview
The **Room Service API Automation Suite** is a **Java + RestAssured + TestNG** based automation framework designed to test a hotel's Room Service REST APIs.  
It includes **positive & negative test cases**, **POJO-based response parsing**, **token-based authentication**, and **beautiful Extent HTML Reports**.

---

## ✨ Features
- ✅ **Positive & ❌ Negative Test Cases**  
- 📦 **POJO Mapping** for JSON responses  
- ♻ **Reusable Base Classes** (`TestBase`, `ExtentManager`)  
- ⚙ **Dynamic Config Loader** from `config.properties`  
- 🔑 **Token Manager** for Authentication  
- 📊 **Detailed HTML Reports** in `target/ExtentReports`  
- 🚀 **Supports Parallel Execution** with TestNG  

---

## 📂 Project Structure
```
RoomServiceApiRestAssured
│── src
│   ├── test
│   │   ├── java
│   │   │   ├── base
│   │   │   │   ├── ExtentManager.java             # Sets up Extent HTML reporting configuration
│   │   │   │   ├── TestBase.java                  # Common setup & teardown for all API tests
│   │   │   ├── dataproviders
│   │   │   │   ├── RoomDataProvider.java          # Provides multiple test data sets from JSON
│   │   │   ├── pojo
│   │   │   │   ├── Room.java                      # POJO representing Room details
│   │   │   ├── tests
│   │   │   │   ├── AddRoomTest.java               # Tests for adding rooms (positive, negative, parameterized)
│   │   │   │   ├── AuthTests.java                 # Tests for authentication and token retrieval
│   │   │   │   ├── DeleteRoomTest.java            # Tests for deleting rooms
│   │   │   │   ├── GetRequestsTests.java          # Tests for fetching room details
│   │   │   │   ├── UpdateRoomTest.java            # Tests for updating room prices
│   │   │   ├── utils
│   │   │       ├── ConfigLoader.java              # Loads config values from properties file
│   │   │       ├── JsonDataLoader.java            # Loads single JSON as Map
│   │   │       ├── JsonMultipleDataLoader.java    # Loads multiple JSON objects as List
│   │   │       ├── TokenManager.java              # Handles authentication code & access token retrieval
│   ├── resources
│   │   ├── testdata
│   │   │   ├── addRoom_invalid.json               # Invalid room data (negative test)
│   │   │   ├── addRoom_multiple.json              # Multiple room data sets (parameterized tests)
│   │   │   ├── addRoom_valid.json                 # Valid room data (positive test)
│   │   ├── config.properties                      # API URLs, credentials & other config values
│   │   ├── extent-config.xml                      # Extent report theme and styling
│── pom.xml                                        # Maven dependencies & build configuration
│── testng.xml                                     # TestNG suite configuration
│── target/ExtentReports/RoomServiceApiReport.html # Generated Extent HTML test report

```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/RitamBhattacharya/RoomServiceApiRestAssured.git
cd RoomServiceApiRestAssured
```

### 2️⃣ Configure API Details  
Edit `src/test/resources/config.properties`:
```properties
Base_URL=https://webapps.tekstac.com/HotelAPI/RoomService
auth_url=https://webapps.tekstac.com/OAuthRestApi/webapi/auth
username=user1
password=pass123
reportDir=target/ExtentReports
```

### 3️⃣ Install Dependencies  
Ensure you have **Java 8+** and **Maven** installed:
```bash
mvn clean install
```

---

## 🚀 Running the Tests

### Run All Tests
```bash
mvn clean test
```

### Run Using TestNG Suite
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

# Room Service API Test Suite

---

## 📜 Test Coverage

| Test Class         | Description                                                                                 |
|--------------------|---------------------------------------------------------------------------------------------|
| `AddRoomTest`      | Tests adding a new room with valid data, invalid room status, and parameterized multiple room additions |
| `AuthTests`        | Tests authentication flow, including obtaining auth code and access token                   |
| `DeleteRoomTest`   | Tests deleting existing rooms by ID and handling deletion attempts for non-existent rooms   |
| `GetRequestsTests` | Tests fetching all rooms, fetching by valid/invalid room ID, fetching by valid/invalid room type, including negative cases |
| `UpdateRoomTest`   | Tests updating room price for valid room IDs and negative tests for invalid/non-existent room IDs |

---

## 📡 API Endpoints

| Method   | Endpoint                    | Description                                   |
|----------|-----------------------------|-----------------------------------------------|
| `POST`   | `/addRoom`                  | Add a new room                                |
| `GET`    | `/viewRoomList`             | Get list of all rooms                         |
| `GET`    | `/viewRoomById/{roomId}`    | Get room details by ID                        |
| `GET`    | `/viewRoomByType`           | Get rooms by type (query param: `roomType`) |
| `PUT`    | `/updateRoomPrice`          | Update room price (form params: `roomId`, `roomPrice`) |
| `DELETE` | `/deleteRoomById/{roomId}`  | Delete room by ID                             |
| `POST`   | `/auth`                     | Generate authentication token                 |

---

### Notes:
- Endpoints for GET by type and update price accept parameters via query/form parameters, not as path variables.
- The authentication flow involves obtaining an auth code and then exchanging it for an access token, used in authorization headers.

---

## 📊 Reporting
After every test run, an **Extent HTML Report** is generated at:
```
target/ExtentReports/RoomServiceApiReport.html
```

---

## 📦 Dependencies
Key dependencies from `pom.xml`:
```xml
<dependencies>
        <dependency>
                <groupId>io.rest-assured</groupId>
                <artifactId>rest-assured</artifactId>
                <version>5.4.0</version>
                <scope>test</scope>
        </dependency>
        <dependency>
                <groupId>org.testng</groupId>
                <artifactId>testng</artifactId>
                <version>7.10.2</version>
                <scope>test</scope>
        </dependency>
        <dependency>
                <groupId>com.aventstack</groupId>
                <artifactId>extentreports</artifactId>
                <version>5.0.9</version>
        </dependency>
        <dependency>
        	<groupId>org.hamcrest</groupId>
                <artifactId>hamcrest</artifactId>
                <version>2.2</version>
        </dependency>
        <dependency>
        	<groupId>com.fasterxml.jackson.core</groupId>
		<artifactId>jackson-databind</artifactId>
		<version>2.17.2</version>
        </dependency>
</dependencies>
```

