# Food Delivery System — Eureka Service Discovery & Delivery Service

A beginner-friendly microservices project built using **Java 17/21**, **Spring Boot 3.3.5**, and **Spring Cloud 2023.0.3**.

---

## 📁 Project Directory Structure

```text
FoodDeliverySystem/
├── eureka-server/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/example/eurekaserver/
│   │       │       └── EurekaServerApplication.java
│   │       └── resources/
│   │           └── application.properties
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml
│
└── delivery-service/
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── com/example/deliveryservice/
    │       │       ├── Delivery.java
    │       │       ├── DeliveryApplication.java
    │       │       └── DeliveryController.java
    │       └── resources/
    │           └── application.properties
    ├── mvnw
    ├── mvnw.cmd
    └── pom.xml
```

---

## 1. Eureka Server Configuration & Code

### 1.1 `eureka-server/pom.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.5</version>
        <relativePath/>
    </parent>
    <groupId>com.example</groupId>
    <artifactId>eureka-server</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>eureka-server</name>
    <description>Eureka Server for Food Delivery System</description>

    <properties>
        <java.version>17</java.version>
        <spring-cloud.version>2023.0.3</spring-cloud.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### 1.2 `eureka-server/src/main/resources/application.properties`
```properties
# Port where Eureka Server runs
server.port=8761

# Application Name
spring.application.name=eureka-server

# Don't register Eureka Server with itself
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

### 1.3 `eureka-server/src/main/java/com/example/eurekaserver/EurekaServerApplication.java`
```java
package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

---

## 2. Delivery Service Configuration & Code

### 2.1 `delivery-service/pom.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.5</version>
        <relativePath/>
    </parent>
    <groupId>com.example</groupId>
    <artifactId>delivery-service</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>delivery-service</name>
    <description>Delivery Service for Food Delivery System</description>

    <properties>
        <java.version>17</java.version>
        <spring-cloud.version>2023.0.3</spring-cloud.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starter Web for RESTful Endpoints -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Eureka Client for Service Discovery -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### 2.2 `delivery-service/src/main/resources/application.properties`
```properties
# Port where Delivery Service runs
server.port=8082

# Application Name registered in Eureka
spring.application.name=delivery-service

# Eureka Server registration URL
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

### 2.3 `delivery-service/src/main/java/com/example/deliveryservice/Delivery.java`
```java
package com.example.deliveryservice;

public class Delivery {

    private int id;
    private String name;

    // No-argument constructor
    public Delivery() {
    }

    // Parameterized constructor
    public Delivery(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

### 2.3.1 `delivery-service/src/main/java/com/example/deliveryservice/Restaurant.java` (and `Restaurent.java`)
```java
package com.example.deliveryservice;

public class Restaurant {

    private int id;
    private String name;

    // No-argument constructor
    public Restaurant() {
    }

    // Parameterized constructor
    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```


### 2.4 `delivery-service/src/main/java/com/example/deliveryservice/DeliveryApplication.java`
```java
package com.example.deliveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }
}
```

### 2.5 `delivery-service/src/main/java/com/example/deliveryservice/DeliveryController.java`
```java
package com.example.deliveryservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {

    // GET /orders
    @GetMapping("/orders")
    public String getOrders() {
        return "Welcome to Delivery Service!";
    }

    // POST /adduser
    @PostMapping("/adduser")
    public String addUser(@RequestBody Delivery delivery) {
        return "User added successfully: " + delivery.getName();
    }
}
```

---

## 3. Step-by-Step Instructions to Run the Projects

### Option A: Running from Terminal / PowerShell (Using Included Maven Wrapper)

> **Order of Execution:** Always start **Eureka Server FIRST**, then start **Delivery Service**.

#### Step 1: Start Eureka Server
1. Open a terminal / PowerShell window.
2. Navigate to the `eureka-server` directory:
   ```bash
   cd c:\Users\Hp\Documents\FoodDeliverySystem\eureka-server
   ```
3. Run the application:
   ```bash
   .\mvnw.cmd spring-boot:run
   ```
   *(On macOS/Linux, use `./mvnw spring-boot:run`)*
4. Eureka Server starts on port `8761`.
5. Open your web browser and visit:
   ```text
   http://localhost:8761
   ```
   You will see the **Spring Eureka Dashboard**. Under *"Instances currently registered with Eureka"*, it will initially state *"No instances available"*.

#### Step 2: Start Delivery Service
1. Open a **second** terminal / PowerShell window.
2. Navigate to the `delivery-service` directory:
   ```bash
   cd c:\Users\Hp\Documents\FoodDeliverySystem\delivery-service
   ```
3. Run the application:
   ```bash
   .\mvnw.cmd spring-boot:run
   ```
4. Delivery Service starts on port `8082`.
5. Refresh the Eureka Dashboard at `http://localhost:8761`. You will see `DELIVERY-SERVICE` registered with status `UP (1) - ...:delivery-service:8082`.

---

### Option B: Running from Spring Tool Suite (STS) / Eclipse

1. In STS, click **File** ➔ **Import...** ➔ **Existing Maven Projects**.
2. Browse to `c:\Users\Hp\Documents\FoodDeliverySystem\eureka-server` and click **Finish**.
3. Repeat the import for `c:\Users\Hp\Documents\FoodDeliverySystem\delivery-service`.
4. Right-click `EurekaServerApplication.java` ➔ **Run As** ➔ **Spring Boot App**.
5. Wait for the Eureka Server console log to indicate started on port 8761.
6. Right-click `DeliveryApplication.java` ➔ **Run As** ➔ **Spring Boot App**.

---

## 4. Testing Endpoints Using Postman

### 1. GET Orders
- **HTTP Method:** `GET`
- **URL:** `http://localhost:8082/orders`
- **Headers:** None
- **Body:** None
- **Expected Response Code:** `200 OK`
- **Expected Response Body (Text):**
  ```text
  Welcome to Delivery Service!
  ```

---

### 2. POST Add User
- **HTTP Method:** `POST`
- **URL:** `http://localhost:8082/adduser`
- **Headers:** `Content-Type: application/json`
- **Body (raw JSON):**
  ```json
  {
    "id": 1,
    "name": "John Doe"
  }
  ```
- **Expected Response Code:** `200 OK`
- **Expected Response Body (Text):**
  ```text
  User added successfully: John Doe
  ```
