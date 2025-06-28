# Course Registration Application

## 🌟 Overview
The **Course Registration Application** is a command-line interface (CLI) program developed in **Java**. It leverages **JDBC (Java Database Connectivity)** and **SQL** for database interaction, enabling seamless management of courses and user registrations. Designed to be robust and user-friendly, this application is ideal for academic institutions or training programs.

---

## ✨ Features
- **User Registration**: Register students with unique IDs.
- **Course Management**: Add, update, delete, and list available courses.
- **Dynamic Database Interaction**: Utilizes SQL for efficient storage and retrieval of data.
- **Real-Time Feedback**: Displays confirmation messages and errors in real-time.
- **CLI Navigation**: Intuitive command-line interface for streamlined interaction.
- **Secure Transactions**: Ensures data integrity and error-free operations.

---

## 🛠️ Prerequisites
- **Java Development Kit (JDK)**: Version 8 or later.
- **Database**: MySQL or any JDBC-compatible SQL database.
- **JDBC Driver**: Ensure the JDBC driver for your database is added to the project.
- **Terminal or Command Prompt**: To run the application.

---

## 🚀 Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/NagellaLokeshwar/Course-Registration-Application.git
   cd Course-Registration-Application
   ```

2. **Set Up the Database**:
   - Create a new database (e.g., `course_registration`).
   - Import the provided SQL schema:
     ```bash
     mysql -u username -p course_registration < schema.sql
     ```
   - Update database credentials in the `DatabaseConfig.java` file.

3. **Compile the Java Files**:
   ```bash
   javac -cp .:mysql-connector-java.jar MainApp.java
   ```

4. **Run the Application**:
   ```bash
   java -cp .:mysql-connector-java.jar MainApp
   ```

---

## 🎮 Usage
1. **Launch the Application**: Start by running `java MainApp`.
2. **Main Menu**: Navigate through options such as:
   - Register a new student.
   - Add a new course.
   - Enroll a student in a course.
   - List all courses or students.
   - Exit the application.
3. **Enter Commands**: Input commands or options as prompted.
4. **View Results**: The application interacts with the database in real-time and displays results immediately.

---

## 💾 Database Schema Overview
The `schema.sql` file defines the following tables:
- **Students**: Stores student information (ID, name, contact details).
- **Courses**: Stores course details (ID, name, description, capacity).
- **Enrollments**: Maps students to their enrolled courses.

---

## 🌟 Future Enhancements
- **Authentication**: Add a login system for admin and students.
- **Search Functionality**: Allow searching for courses or students by name.
- **GUI Version**: Develop a graphical user interface for better usability.
- **Email Notifications**: Integrate email services to notify students of course updates.

---

## 🤝 Contribution
Contributions are welcome! Follow these steps to contribute:
1. Fork this repository.
2. Create a branch for your feature:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add feature-name"
   ```
4. Push the branch:
   ```bash
   git push origin feature-name
   ```
5. Submit a pull request.

---

## 📝 License
This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.

---

**Simplify course registration with ease!** 🎓
