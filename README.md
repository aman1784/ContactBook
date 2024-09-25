# Contact Book Application

## Overview
This is a simple Contact Book application built in Java using JDBC to interact with a MySQL database. The application allows users to manage their contacts by adding, viewing, and searching contacts by name or number.

## Features
- Add new contacts
- View all contacts
- Search contacts by name
- Search contacts by phone number
- Search contacts by either name or phone number

## Requirements
- Java Development Kit (JDK)
- MySQL Database
- JDBC Driver for MySQL

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/aman1784/ContactBook
   cd <directory>
   javac Main.java
   java Main

2. **Setup the MySQL Database**
    
    Create a new database (e.g., contact_book).
    Create a table named contactBook with the following structure:

    ```sql
    CREATE TABLE contactBook (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        phoneNumber VARCHAR(15) NOT NULL,
        emailId VARCHAR(255) NOT NULL
    );
