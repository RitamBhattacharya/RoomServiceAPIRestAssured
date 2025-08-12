# 🏨 Room Service API Automation Suite

![Java](https://img.shields.io/badge/Java-11-blue)
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
│   │   │   │   ├── ExtentManager.java       # Sets up Extent HTML report
│   │   │   │   ├── TestBase.java            # Common setup/teardown for tests
│   │   │   ├── pojo
│   │   │   │   ├── Room.java                # Room POJO model
│   │   │   ├── tests
│   │   │   │   ├── AddRoomTest.java         # Add Room positive & negative tests
│   │   │   │   ├── AuthTests.java           # Authentication tests
│   │   │   │   ├── DeleteRoomTest.java      # Delete Room tests
│   │   │   │   ├── GetRequestsTests.java    # GET API tests
│   │   │   │   ├── UpdateRoomTest.java      # Update Room Price tests
│   │   │   ├── utils
│   │   │       ├── ConfigLoader.java        # Loads config from properties file
│   │   │       ├── TokenManager.java        # Handles token generation
│   ├── resources
│       ├── config.properties                # API config & credentials
│       ├── extent-config.xml                 # Extent report theme config
│── pom.xml                                   # Maven dependencies
│── testng.xml                                # TestNG suite config
│── target/ExtentReports/RoomServiceApiReport.html  # Generated HTML report
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
Ensure you have **Java 11+** and **Maven** installed:
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

## 📜 Test Coverage

| Test Class        | Description |
|-------------------|-------------|
| `AddRoomTest`     | Tests adding a new room with valid & invalid data |
| `AuthTests`       | Tests authentication flow & token retrieval |
| `DeleteRoomTest`  | Tests deleting existing & non-existing rooms |
| `GetRequestsTests`| Tests fetching room list, by ID, by type & invalid cases |
| `UpdateRoomTest`  | Tests updating room price with valid & invalid IDs |

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/addRoom` | Add a new room |
| `GET`  | `/getAllRooms` | Get list of all rooms |
| `GET`  | `/getRoomById/{roomId}` | Get room details by ID |
| `GET`  | `/getRoomByType/{roomType}` | Get rooms by type |
| `PUT`  | `/updateRoomPrice/{roomId}` | Update room price |
| `DELETE` | `/deleteRoomById/{roomId}` | Delete room by ID |
| `POST` | `/auth` | Generate authentication token |

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
    </dependencies>
```

