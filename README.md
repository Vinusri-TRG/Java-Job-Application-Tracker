# Job Application & Interview Tracker

A Java-based web application built using Spring Boot that helps users manage job applications and track interview progress in one place. The application supports user registration, secure login, and individual job application management.

**Live Demo:** https://jobtracker-web-kgvk.onrender.com

**GitHub Repository:** https://github.com/Vinusri-TRG/Java-Job-Application-Tracker

## Project Evolution

This project was initially developed as a Java console application using JDBC and MySQL. It was later upgraded into a web application using Spring Boot, Spring Data JPA, Hibernate, and Thymeleaf.

The application was further enhanced with user authentication, individual user data management, PostgreSQL database integration, and online deployment.

## Features

### User Authentication
- User registration with email and password.
- Secure login and logout using Spring Security.
- Password encryption using BCrypt.
- Individual job application data for each registered user.
- Users can access and manage only their own applications.

### Job Application Management
- Add new job applications.
- View job applications on a dashboard.
- Update application details.
- Delete job applications.
- Track application status from Applied to 5th Round.
- Track interview status and interview dates.
- Dashboard displaying application statistics.
- Search applications by company name or job role.
- Filter applications by status.
- Form validation for required fields.

## Technologies Used

- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate
- Thymeleaf
- HTML and CSS
- JavaScript
- PostgreSQL
- Neon (Cloud Database)
- Maven
- Git and GitHub
- Render (Deployment)

## Project Screenshots

### 1. Dashboard

[View Dashboard](https://github.com/Vinusri-TRG/Java-Job-Application-Tracker/blob/deployment/screenshots/dashboard.png)

### 2. Add Application

[View Add Application](https://github.com/Vinusri-TRG/Java-Job-Application-Tracker/blob/deployment/screenshots/add-application.png)

### 3. Edit Application

[View Edit Application](https://github.com/Vinusri-TRG/Java-Job-Application-Tracker/blob/deployment/screenshots/edit-application.png)

## Database Details

- Database: `neondb`
- Database system: PostgreSQL
- Cloud database provider: Neon
- Application table: `job_applications`
- User table: `app_users`
- Database connectivity: Spring Data JPA and Hibernate.

## Deployment

The application is deployed using Render, with Neon PostgreSQL as the cloud database.

- Hosting platform: Render
- Database hosting: Neon
- Application URL: https://jobtracker-web-kgvk.onrender.com

## How to Run Locally

### Prerequisites

- JDK 17 or later
- PostgreSQL database
- Maven
- Visual Studio Code or another Java IDE

### 1. Clone the repository

```bash
git clone https://github.com/Vinusri-TRG/Java-Job-Application-Tracker.git
```

### 2. Configure database credentials

Set the following environment variables in PowerShell using your PostgreSQL database credentials:

```powershell
$env:DB_URL="your_postgresql_jdbc_url"
$env:DB_USERNAME="your_database_username"
$env:DB_PASSWORD="your_database_password"
```

Do not upload database passwords or other secrets to GitHub.

### 3. Run the application

Open the project folder in the terminal and execute:

```powershell
.\mvnw.cmd spring-boot:run
```

### 4. Open the application

Visit:

http://localhost:8080

Register an account and log in to start managing job applications.

## Future Improvements

- Application reminders and notifications.
- More detailed dashboard analytics.
- Export job application records.

## Author

B.Sc. Information Technology Student