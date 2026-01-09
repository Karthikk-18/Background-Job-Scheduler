# 📦 Distributed Job Scheduler

A distributed job scheduling system built with Spring Boot that allows users to schedule and manage various types of jobs (Email, SMS, Webhooks) in a scalable and reliable manner.

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 🌟 Features

- **📧 Email Job Scheduling** - Schedule and send emails automatically
- **🔐 User Authentication** - Secure login and registration system
- **🎨 Modern UI** - Beautiful, responsive web interface with glassmorphism design
- **⚡ RESTful API** - Easy-to-use REST endpoints for job management
- **🔒 Spring Security** - Password encryption and session management
- **💾 Database Integration** - Support for H2 (development) and PostgreSQL (production)
- **📱 Responsive Design** - Mobile-friendly interface

## 🏗️ Architecture

The system follows a distributed microservices architecture:

```
┌─────────────┐      ┌──────────────┐      ┌─────────────┐
│   Web UI    │─────▶│  Job Service │─────▶│   Database  │
└─────────────┘      └──────────────┘      └─────────────┘
                             │
                             ▼
                     ┌──────────────┐
                     │ Job Executor │
                     └──────────────┘
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL (for production) or H2 (for development)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Karthikk-18/Distributed-Job-Scheduler.git
   cd Distributed-Job-Scheduler
   ```

2. **Configure the database**
   
   Edit `src/main/resources/application.properties`:
   
   For H2 (Development):
   ```properties
   spring.datasource.url=jdbc:h2:mem:jobscheduler
   spring.datasource.username=sa
   spring.datasource.password=
   ```
   
   For PostgreSQL (Production):
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/jobscheduler
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application**
   
   Open your browser and navigate to: `http://localhost:8080`

## 📚 API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/login` | Login page |
| POST | `/perform-login` | Authenticate user |
| GET | `/register` | Registration page |
| POST | `/register` | Create new account |
| POST | `/logout` | Logout user |

### Job Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Dashboard - View all job types |
| GET | `/email-job` | Email job creation form |
| POST | `/jobs` | Submit a new job |

### Job Submission Example

```bash
curl -X POST http://localhost:8080/jobs \
  -H "Content-Type: application/json" \
  -d '{
    "type": "EMAIL",
    "payload": "{\"to\":\"user@example.com\",\"subject\":\"Test\",\"body\":\"Hello\"}"
  }'
```

## 🗂️ Project Structure

```
Distributed-Job-Scheduler/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/jobscheduler/
│   │   │       ├── config/
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java
│   │   │       │   └── JobController.java
│   │   │       ├── entity/
│   │   │       │   ├── User.java
│   │   │       │   └── Job.java
│   │   │       ├── repository/
│   │   │       │   ├── UserRepository.java
│   │   │       │   └── JobRepository.java
│   │   │       ├── service/
│   │   │       │   ├── UserService.java
│   │   │       │   ├── CustomUserDetailsService.java
│   │   │       │   └── JobService.java
│   │   │       └── JobSchedulerApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   ├── email-job.html
│   │       │   ├── login.html
│   │       │   └── register.html
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── style.css
│   │       │   └── js/
│   │       │       └── app.js
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🛠️ Technologies Used

- **Backend**: Spring Boot 3.x
- **Security**: Spring Security with BCrypt password encoding
- **Database**: Spring Data JPA with PostgreSQL/H2
- **Frontend**: HTML5, CSS3 (with Glassmorphism), JavaScript
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven

## 🎨 UI Screenshots

### Login Page
Beautiful gradient-based login interface with form validation

### Dashboard
Modern glassmorphism design showing available job types

### Email Job Form
Intuitive form for creating and scheduling email jobs

## 🔒 Security Features

- **Password Encryption**: BCrypt hashing algorithm
- **Session Management**: Secure session handling with Spring Security
- **CSRF Protection**: Built-in CSRF token validation
- **Role-Based Access**: User role management (USER, ADMIN)
- **Form Authentication**: Custom login and logout functionality

## 🌐 Future Enhancements

- [ ] SMS Job Scheduling
- [ ] Webhook Job Scheduling
- [ ] Job Scheduling with Cron Expressions
- [ ] Job History and Monitoring Dashboard
- [ ] Email Notifications for Job Status
- [ ] Distributed Job Execution with Multiple Workers
- [ ] Job Priority Management
- [ ] Retry Mechanism for Failed Jobs
- [ ] API Rate Limiting
- [ ] Docker Containerization
- [ ] Kubernetes Deployment

## 📝 Configuration

### Application Properties

Key configuration options in `application.properties`:

```properties
# Server Configuration
server.port=8080

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/jobscheduler
spring.jpa.hibernate.ddl-auto=update

# Security
spring.security.user.name=admin
spring.security.user.password=admin123

# Logging
logging.level.org.springframework.security=DEBUG
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

**Karthikk-18**

- GitHub: [@Karthikk-18](https://github.com/Karthikk-18)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Spring Security for robust authentication
- Thymeleaf for powerful templating

## 📞 Support

For support, please open an issue in the GitHub repository or contact the maintainer.

---

⭐ If you found this project helpful, please give it a star!

Made with ❤️ by Karthikk-18
