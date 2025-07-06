# JDBC Employee Manager App

## 📌 Description
Java console application that connects to a MySQL database to manage employees using JDBC.

## 🛠 Features
- Add Employee
- View All Employees
- Update Employee Details
- Delete Employee

## 💡 Concepts Used
- JDBC Connection
- PreparedStatement
- ResultSet
- SQL CRUD operations

## ⚙️ How to Run
1. Create MySQL database:
   ```sql
   CREATE DATABASE employee_db;
   USE employee_db;
   CREATE TABLE employees (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       position VARCHAR(100)
   );
