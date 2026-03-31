# 📚 Library Management System

## 📌 Description
Library Management System built using Java and MySQL with a modern GUI to manage books, members, and transactions efficiently.

---

## 📖 Overview
The **Library Management System** is a desktop-based application designed to simplify and automate library operations. It provides an intuitive interface and powerful features for managing books, members, and issue/return processes.

---

## 🚀 Features
- 🔐 User Login Authentication  
- 📖 Add, Update, Delete Books  
- 👤 Manage Members (Add / Remove / Update)  
- 🔄 Issue and Return Books  
- 🔍 Search Books and Members  
- 📊 Track Records and Transactions  
- 💾 MySQL Database Integration  
- 🎨 Modern GUI (Java Swing)  

---

## 🛠️ Technologies Used
- **Java (Swing)** – Frontend GUI  
- **MySQL** – Database  
- **JDBC** – Database Connectivity  
- **VS Code / IntelliJ** – Development Environment  

---

## 📂 Project Structure
Library-Management-System/
├── src/
│   ├── LoginGUI.java        # Login interface
│   ├── AddBook.java         # Add new books 
│   ├── AddMember.java       # Add new members
│   ├── IssueBook.java       # Issue books
│   ├── ReturnBook.java      # Return books
│   ├── ViewBook.java        # View book records
│   ├── ViewMembers.java     # View member details
│   ├── Library GUI.java     # Core logic
│   ├── DBConnection.java    # Database connection
│   └── Main.java            # Application entry point
├── database/
│   ├── Database.sql
│   ├── Book_members_issues.sql
│   ├── Name_and_password.sql
│   └── Users.sql
└── README.md

## ⚙️ Setup & Execution Guide

### 🔧 Step 1: Clone Repository
```bash
https://github.com/NISHIT-coder/Library-Management-System-LMS-.git
```
💻 Step 2: Open Project

Import the project into your preferred IDE:

* VS Code
* IntelliJ IDEA
* Eclipse

🗄️ Step 3: Database Setup
* Open MySQL
* Create a new database
* Import the following SQL files from the /database folder:
    * Database.sql
    * Book_members_issues.sql
    * Name_and_password.sql
    * Users.sql

🔑 Step 4: Configure Database

Update your database credentials in:
 ```bash
 DBConnection.java
```
▶️ Step 5: Run Application

Run the main file:
```bash
LoginGUI.java
Library GUI.java
```
      
