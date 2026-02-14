# 🛒 Spring Boot E-Commerce Web Application (Thymeleaf)

A full-stack E-Commerce Web Application similar to Amazon, built using Spring Boot and Thymeleaf.  
This project demonstrates backend development, authentication, database integration, and dynamic UI rendering.

---

## 🚀 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Thymeleaf
- Spring Data JPA (Hibernate)
- MySQL
- Maven

---

## ✨ Features

### 👤 User Features
- User Registration & Login
- Secure Authentication using Spring Security
- Browse Products
- View Product Details
- Add to Cart
- Remove from Cart
- Place Orders

### 🛠 Admin Features
- Add Products
- Update Products
- Delete Products
- Manage Inventory

---

## 📂 Project Structure

com.example.ShopingWeb
│
├── controller
├── service
├── repository
├── entity
├── config
└── templates


---

## 📸 UI Screenshots

- Home Page
 <img width="1347" height="680" alt="image" src="https://github.com/user-attachments/assets/a869b3ad-8a8e-4ad4-a60a-5be01898ab1c" />
- Product Listing Page
<img width="1352" height="676" alt="Screenshot 2026-02-14 144137" src="https://github.com/user-attachments/assets/04552ef7-e853-46a6-a21b-9808275eb430" />
- Cart Page
<img width="1343" height="664" alt="Screenshot 2026-02-14 144211" src="https://github.com/user-attachments/assets/3a3a75ec-4a89-4944-baab-65247e0600f0" />
- Login Page
<img width="400" height="326" alt="Screenshot 2026-02-14 144304" src="https://github.com/user-attachments/assets/ad4d8e74-be88-4b53-9695-5dd3d6837a15" />
- Registration Page
<img width="398" height="330" alt="Screenshot 2026-02-14 144243" src="https://github.com/user-attachments/assets/6ff21583-db6c-4d4e-bad7-292e45de98e3" />
- Admin Dashboard
<img width="1344" height="678" alt="Screenshot 2026-02-14 144340" src="https://github.com/user-attachments/assets/9e32e622-635a-4232-97c9-dafa01d1a383" />
---

## ⚙️ Installation & Setup

### 1️⃣ Clone the repository

```bash
git clone https://github.com/manish01-star/spring-boot-shopping-web


## 🗄 Database Setup

1. Import the database using MySQL:

```bash
mysql -u root -p < database/shoppingdb.sql

2. Update application.properties with your MySQL username 

spring.datasource.url=jdbc:mysql://localhost:3306/shoppingdb
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update





