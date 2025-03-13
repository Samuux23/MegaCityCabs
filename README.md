# MegaCityCabs

## 🚖 About the Project
MegaCityCabs is a **Java-based cab booking system** designed to streamline cab management, including **customer bookings, driver assignments, fare calculations, and user interface enhancements**. This system is built using **Java (JSP & Servlets)** and follows a structured software development approach.

## 📂 Project Structure
```
megacity/
│-- src/                  # Source code (Java, JSP, Servlets)
│-- pom.xml               # Maven build file
│-- .git/                 # Git repository metadata
│-- target/               # Compiled files (generated after Maven build)
│-- .settings/, .classpath, .project  # Eclipse project files
```

## 🛠️ Technologies Used
- **Java (JSP & Servlets)**
- **Apache Tomcat** (Recommended version: 9+)
- **Maven** for dependency management
- **MySQL** for database management
- **HTML, CSS, JavaScript** for frontend UI

## 🚀 Installation & Setup
### 1️⃣ Prerequisites
Before running this project, ensure you have the following installed:
- [Java JDK 8+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [MySQL Database](https://dev.mysql.com/downloads/installer/)
- [Apache Tomcat 9+](https://tomcat.apache.org/download-90.cgi)

### 2️⃣ Clone the Repository
```sh
git clone https://github.com/Samuux23/MegaCityCabs.git
cd MegaCityCabs
```

### 3️⃣ Configure the Database
1. Start MySQL and create a database named `megacitycabs`.
2. Import the database schema (SQL file) if provided.
3. Update the **database credentials** in `src/main/resources/config.properties`.

### 4️⃣ Build & Run the Application
#### Using Maven
```sh
mvn clean install
mvn tomcat7:run
```
#### Manually Deploying to Tomcat
1. Build the project:
   ```sh
   mvn package
   ```
2. Deploy the generated WAR file (`target/MegaCityCabs.war`) into the **Tomcat `webapps/` folder**.
3. Start Tomcat and access the application at:
   ```
   http://localhost:8080/MegaCityCabs
   ```

## 🛠️ Features
✅ User authentication (Login/Register)  
✅ Customer booking system  
✅ Driver management  
✅ Booking history & invoice generation  
✅ Responsive UI using JSP & Bootstrap  

## 📸 Screenshots
To better understand the user interface of the MegaCityCabs application, here are some screenshots:

### Home Page
![Home UI](https://github.com/Samuux23/MegaCityCabs/blob/main/Screenshot%202025-03-12%20010141.png?raw=true)

### Dashboard
![Dashboard UI](https://raw.githubusercontent.com/Samuux23/MegaCityCabs/main/dashboard-ui.png)

### Booking Page
![Booking UI](https://raw.githubusercontent.com/Samuux23/MegaCityCabs/main/booking-ui.png)

## 🤝 Contributing
Contributions are welcome! If you would like to improve this project:
1. Fork the repository
2. Create a new branch (`feature/new-feature`)
3. Commit your changes (`git commit -m 'Added new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Submit a pull request

## 📄 License
This project is **open-source** and available under the **MIT License**.

---
🌟 **Star this repo** if you found it useful!

