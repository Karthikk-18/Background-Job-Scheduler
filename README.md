# 📦 Distributed Job Scheduler

A distributed job scheduling system built with Spring Boot that allows users to schedule and manage various types of jobs (Email, SMS, Webhooks) in a scalable and reliable manner.

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)

## 🌟 Features

- **📧 Email Job Scheduling** - Schedule and send emails automatically
- **🔐 User Authentication** - Secure login and registration system
- **🎨 Modern UI** - Beautiful, responsive web interface with glassmorphism design
- **⚡ RESTful API** - Easy-to-use REST endpoints for job management
- **🔒 Spring Security** - Password encryption and session management
- **💾 Database Integration** - MySQL 

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

## 🛠️ Technologies Used

- **Backend**: Spring Boot 4.0.1
- **Security**: Spring Security with BCrypt password encoding
- **Database**: Spring Data JPA with MySql/H2
- **Frontend**: HTML5, CSS3 (with Glassmorphism), JavaScript
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven

## 🎨 UI

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

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👨‍💻 Author

**Karthikk-18**

- GitHub: [@Karthikk-18](https://github.com/Karthikk-18)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Spring Security for robust authentication
- Thymeleaf for powerful templating

---

⭐ If you found this project helpful, please give it a star!
