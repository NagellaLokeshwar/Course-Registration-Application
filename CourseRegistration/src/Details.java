import db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Details {

	static Scanner sc = new Scanner(System.in);

	public void start() {
		startUI();
		try {
			DatabaseConnection.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startUI() {
		while (true) {
			System.out.println("\n--- Course Registration ---");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Enter: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				registerType();
				break;
			case 2:
				loginType();
				break;
			case 3:
				System.out.println("Thank you for using the system. Goodbye!");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void registerType() {
		while (true) {
			System.out.println("\n--- Registration ---");
			System.out.println("1. User Register");
			System.out.println("2. Professor Register");
			System.out.println("3. Back to Main Menu");
			System.out.print("Enter: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1: {

				System.out.println("Please fill the Deatils...!");
				RegisterDetails rd = new RegisterDetails();
				String User_Name = null;
				String Email_Id = null;
				String Password = null;

				while (true) {
					System.out.print("Enter the UserName :");
					User_Name = sc.nextLine();

					if (!checkusernameAvailability(User_Name)) {
						rd.setUser_Name(User_Name);
						break;
					} else {
						System.out.println("UserName already exists. Choose another!");
					}
				}

				while (true) {
					System.out.print("Enter the User Email ID :");
					Email_Id = sc.nextLine();
					if (!checkEmailAvailability(Email_Id)) {
						rd.setEmailID(Email_Id);
						break;
					} else {
						System.out.println("Email already exists. Choose another!");
					}
				}

				System.out.print("Enter the Password :");
				Password = sc.nextLine();

				if (storeUserDetailsInDatabase(User_Name, Email_Id, Password)) {
					System.out.println("Registration successful!");
					startUI();

				} else {
					System.out.println("Registration failed. Please try again.");
				}
				break;
			}

			case 2: {
				System.out.println("Please fill the Deatils...!");
				RegisterDetails rd = new RegisterDetails();
				String Email_Id = null;
				String Password = null;
				String User_Name = null;

				while (true) {
					System.out.print("Enter the Professor User Name:");
					User_Name = sc.nextLine();
					if (!checkProfusernameAvailability(User_Name)) {
						rd.setUser_Name(User_Name);
						break;
					} else {
						System.out.println("User Name already exists. Choose another!");
					}
				}
				while (true) {
					System.out.print("Enter the Professor Email ID :");
					Email_Id = sc.nextLine();
					if (!checkprofnameAvailability(Email_Id)) {
						rd.setEmailID(Email_Id);
						break;
					} else {
						System.out.println("Email already exists. Choose another!");
					}
				}

				System.out.print("Enter the Password :");
				Password = sc.nextLine();

				if (storeProfDetailsInDatabase(User_Name, Email_Id, Password)) {
					System.out.println("Registration successful!");
					startUI();
				} else {
					System.out.println("Registration failed. Please try again.");
				}
				break;
			}

			case 3:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void loginType() {
		while (true) {
			System.out.println("\n--- Login ---");
			System.out.println("1. Admin Login");
			System.out.println("2. User Login");
			System.out.println("3. Back to Main Menu");
			System.out.print("Enter: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				adminMenu();
				break;
			case 2:
				userLogin();
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void adminMenu() {
		while (true) {
			System.out.println("\n--- Admin Menu ---");
			System.out.println("1. Register Professor");
			System.out.println("2. Register User");
			System.out.println("3. View Courses");
			System.out.println("4. Register Course");
			System.out.println("5. View Users");
			System.out.println("6. Delete Course");
			System.out.println("7. Delete User");
			System.out.println("8. Back to Login Menu");
			System.out.print("Enter: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				admin_regis_prof();
				break;
			case 2:
				admin_regis_user();

				break;
			case 3:
				viewCourses();
				break;
			case 4:
				registerCourse(sc);
				break;
			case 5:
				viewUsers();
				break;
			case 6:
				deleteCourse(sc);
				break;
			case 7:
				deleteUser(sc);
				break;
			case 8:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void userLogin() {
		System.out.println("\n--- User Login ---");
		System.out.println("1. Student Login");
		System.out.println("2. Professor Login");
		System.out.print("Enter: ");
		int choice = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			if (validateLogin()) {
				studentMenu();
			} else {
				System.out.println("Incorrect login details.");
			}
			break;
		case 2:
			if (validateprofLogin()) {
				professorMenu();
			} else {
				System.out.println("Incorrect login details.");
			}
			break;
		default:
			System.out.println("Invalid choice. Returning to Login Menu.");
		}
	}

	private void studentMenu() {
		while (true) {
			System.out.println("\n--- Student Menu ---");
			System.out.println("1. View Course");
			System.out.println("2. Enroll for the Course");
			System.out.println("3. View Enrolled Courses");
			System.out.println("4. Change Password");
			System.out.println("5. View Scores");
			System.out.println("6. View Total Report Card");
			System.out.println("7. Back to Login Menu");
			System.out.print("Choose the option: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				viewCourses();
				break;
			case 2:
				enrollForCourse(sc);
				break;
			case 3:
				viewEnrolledCourses(sc, "");
				break;
			case 4:
				changePassword();
				break;
			case 5:
				viewScores();
				break;
			case 6:
				viewReportCard();
				break;
			case 7:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void viewReportCard() {
	    // Assuming student is logged in and we have their student ID
	    System.out.print("Enter your Student ID: ");
	    int studentId = sc.nextInt(); // Input Student ID

	    // Create a connection object (static connection as mentioned previously)
	    Connection conn = DatabaseConnection.getConnection();

	    if (conn == null) {
	        System.out.println("Unable to connect to the database.");
	        return;
	    }

	    try {
	        // Query to fetch all courses and scores for the student
	        String reportCardQuery = """
	            SELECT c.Course_Name, sc.score
	            FROM student_scores sc
	            INNER JOIN enroll_details e ON sc.course_id = e.Course_ID
	            INNER JOIN course c ON e.Course_ID = c.Course_ID
	            WHERE sc.student_id = ?
	        """;

	        try (PreparedStatement stmt = conn.prepareStatement(reportCardQuery)) {
	            stmt.setInt(1, studentId); // Set the student ID

	            ResultSet rs = stmt.executeQuery();

	            // Check if the student has any courses
	            if (!rs.next()) {
	                System.out.println("No courses found for this student.");
	                return;
	            }

	            // Print the report card header
	            System.out.println("------------------------------------------------------");
	            System.out.println("               Report Card for Student ID: " + studentId);
	            System.out.println("------------------------------------------------------");
	            System.out.printf("%-30s %-10s%n", "Course Name", "Score");
	            System.out.println("------------------------------------------------------");

	            // Loop through and print each course and score
	            do {
	                String courseName = rs.getString("Course_Name");
	                int score = rs.getInt("score");
	                System.out.printf("%-30s %-10d%n", courseName, score);
	            } while (rs.next());

	            System.out.println("------------------------------------------------------");
	        }
	    } catch (SQLException e) {
	        System.out.println("Database error: " + e.getMessage());
	    }
	}


	private void viewScores() {
	    System.out.print("Enter your Student ID: ");
	    int studentId = sc.nextInt();  // Assuming student enters their ID

	    System.out.print("Enter the Course ID: ");
	    String courseId = sc.next();  // Assuming student enters the Course ID

	    // Create a connection object (static connection as mentioned previously)
	    Connection conn = DatabaseConnection.getConnection();

	    if (conn == null) {
	        System.out.println("Unable to connect to the database.");
	        return;
	    }

	    try {
	        // Query to retrieve the student's score for the specific course
	        String viewScoresQuery = """
	            SELECT score 
	            FROM student_scores 
	            WHERE student_id = ? AND course_id = ?
	        """;

	        try (PreparedStatement stmt = conn.prepareStatement(viewScoresQuery)) {
	            stmt.setInt(1, studentId); // Set the student ID
	            stmt.setString(2, courseId); // Set the course ID

	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                int score = rs.getInt("score");
	                System.out.println("Your score for Course ID " + courseId + " is: " + score);
	            } else {
	                System.out.println("No score found for the specified student and course.");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Database error: " + e.getMessage());
	    }
	}


	private void professorMenu() {
		while (true) {
			System.out.println("\n--- Professor Menu ---");
			System.out.println("1. View Course");
			System.out.println("2. View Courses Handled");
			System.out.println("3. Grade Students for the Subjects Handled");
			System.out.println("4. Back to Login Menu");
			System.out.print("Choose the option: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				viewCourses();
				break;
			case 2:
				viewCoursesHandled();
				break;
			case 3:
				gradeStudents();
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	public static void gradeStudents() {
	    System.out.print("Enter the Course ID you handle: ");
	    String courseId = sc.next();
	    
	    System.out.print("Enter your Professor ID: ");
	    int professorId = sc.nextInt();

	    Connection conn = DatabaseConnection.getConnection(); // Assuming static connection

	    if (conn == null) {
	        System.out.println("Unable to connect to the database.");
	        return;
	    }

	    try {
	        // Query to fetch students enrolled in the course
	        String fetchStudentsQuery = """
	            SELECT u.id AS student_id, u.User_Name AS student_name
	            FROM enroll_details e
	            INNER JOIN user_details u ON e.Email_id = u.Email_id
	            WHERE e.Course_ID = ?
	        """;

	        try (PreparedStatement fetchStmt = conn.prepareStatement(fetchStudentsQuery)) {
	            fetchStmt.setString(1, courseId);

	            ResultSet rs = fetchStmt.executeQuery();

	            System.out.println("Students Enrolled:");
	            System.out.println("----------------------------");
	            while (rs.next()) {
	                int studentId = rs.getInt("student_id");
	                String studentName = rs.getString("student_name");
	                System.out.printf("Student ID: %d, Name: %s%n", studentId, studentName);

	                System.out.print("Enter marks for this student: ");
	                int marks = sc.nextInt();

	                // Insert or update student scores
	                String insertScoreQuery = """
	                    INSERT OR REPLACE INTO student_scores (id, student_id, course_id, professor_id, score)
	                    VALUES ((SELECT id FROM student_scores WHERE student_id = ? AND course_id = ? AND professor_id = ?), ?, ?, ?, ?)
	                """;
	                try (PreparedStatement scoreStmt = conn.prepareStatement(insertScoreQuery)) {
	                    scoreStmt.setInt(1, studentId); // Check for existing record
	                    scoreStmt.setString(2, courseId);
	                    scoreStmt.setInt(3, professorId);
	                    scoreStmt.setInt(4, studentId); // Insert new or replace
	                    scoreStmt.setString(5, courseId);
	                    scoreStmt.setInt(6, professorId);
	                    scoreStmt.setInt(7, marks);

	                    int rows = scoreStmt.executeUpdate();
	                    if (rows > 0) {
	                        System.out.println("Marks assigned successfully.");
	                        conn.commit();
	                    } else {
	                        System.out.println("Failed to assign marks.");
	                    }
	                }
	            }
	            System.out.println("----------------------------");
	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        System.out.println("Database error: " + e.getMessage());
	    }
	}


	private void viewCoursesHandled() {
	    // Assuming professor is logged in and we have their professor ID
	    System.out.print("Enter your Professor ID: ");
	    int professorId = sc.nextInt(); // Input Professor ID

	    // Create a connection object (static connection as mentioned previously)
	    Connection conn = DatabaseConnection.getConnection();

	    if (conn == null) {
	        System.out.println("Unable to connect to the database.");
	        return;
	    }

	    try {
	        // Query to fetch all courses handled by the professor
	        String viewCoursesQuery = """
	            SELECT Course_ID, Course_Name, Course_Desp
	            FROM course
	            WHERE Professor_ID = ?
	        """;

	        try (PreparedStatement stmt = conn.prepareStatement(viewCoursesQuery)) {
	            stmt.setInt(1, professorId); // Set the professor ID

	            ResultSet rs = stmt.executeQuery();

	            // Check if the professor handles any courses
	            if (!rs.next()) {
	                System.out.println("No courses found for this Professor ID.");
	                return;
	            }

	            // Print the courses header
	            System.out.println("------------------------------------------------------");
	            System.out.println("              Courses Handled by Professor ID: " + professorId);
	            System.out.println("------------------------------------------------------");
	            System.out.printf("%-10s %-30s %-50s%n", "Course ID", "Course Name", "Description");
	            System.out.println("------------------------------------------------------");

	            // Loop through and print each course
	            do {
	                int courseId = rs.getInt("Course_ID");
	                String courseName = rs.getString("Course_Name");
	                String courseDesp = rs.getString("Course_Desp");
	                System.out.printf("%-10d %-30s %-50s%n", courseId, courseName, courseDesp);
	            } while (rs.next());

	            System.out.println("------------------------------------------------------");
	        }
	    } catch (SQLException e) {
	        System.out.println("Database error: " + e.getMessage());
	    }
	}


	private static boolean storeUserDetailsInDatabase(String User_Name, String Email_ID, String Password) {
		Connection conn = DatabaseConnection.getConnection();

		try {
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO user_details (User_Name,email_id, password) VALUES (?, ?, ?)");
			pstmt.setString(1, User_Name);
			pstmt.setString(2, Email_ID);
			pstmt.setString(3, Password);
			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	private static boolean storeProfDetailsInDatabase(String User_Name, String Email_ID, String Password) {
		Connection conn = DatabaseConnection.getConnection();

		try {
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO prof_details (User_Name,email_id, password) VALUES (?, ? , ?)");
			pstmt.setString(1, User_Name);
			pstmt.setString(2, Email_ID);
			pstmt.setString(3, Password);
			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	private static boolean validateLogin() {
		System.out.print("Enter your registered User_Name: ");
		String User_Name = sc.next();

		System.out.print("Enter your registered Email ID: ");
		String email = sc.next();

		System.out.print("Enter your Password: ");
		String password = sc.next();

		Connection conn = DatabaseConnection.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM user_details WHERE User_Name = ? AND email_id = ? AND password = ?");) {

			pstmt.setString(1, User_Name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);

			ResultSet rs = pstmt.executeQuery();
			return rs.next();

		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
			return false;
		}
	}

	public static void changePassword() {
		System.out.println("Change Password");

		// Prompt for login details
		System.out.print("Enter your Username: ");
		String userName = sc.next();

		System.out.print("Enter your Email ID: ");
		String email = sc.next();

		System.out.print("Enter your Current Password: ");
		String currentPassword = sc.next();

		Connection conn = DatabaseConnection.getConnection();
		try {
			if (conn == null) {
				System.out.println("Unable to connect to the database.");
				return;
			}

			String validateQuery = "SELECT * FROM user_details WHERE User_Name = ?  AND password = ?";
			try (PreparedStatement validateStmt = conn.prepareStatement(validateQuery)) {
				validateStmt.setString(1, userName);

				validateStmt.setString(2, currentPassword); // For hashed passwords, replace with comparison logic

				ResultSet rs = validateStmt.executeQuery();

				if (rs.next()) {
					System.out.print("Enter your New Password: ");
					String newPassword = sc.next();

					System.out.print("Confirm your New Password: ");
					String confirmPassword = sc.next();

					if (!newPassword.equals(confirmPassword)) {
						System.out.println("Passwords do not match. Please try again.");
						return;
					}

					// Update the password in the database
					String updateQuery = "UPDATE user_details SET password = ? WHERE User_Name = ? ";
					try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
						updateStmt.setString(1, newPassword); // Hash the password if applicable
						updateStmt.setString(2, userName);

						int rowsAffected = updateStmt.executeUpdate();
						if (rowsAffected > 0) {
							System.out.println("Password updated successfully!");
							conn.commit();
						} else {
							System.out.println("Failed to update the password. Please try again.");
						}
					}
				} else {
					System.out.println("Invalid login details. Unable to change password.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
	}

	private static boolean validateprofLogin() {
		System.out.print("Enter your registered User_Name: ");
		String User_Name = sc.next();

		System.out.print("Enter your registered Email ID: ");
		String email = sc.next();

		System.out.print("Enter your Password: ");
		String password = sc.next();

		Connection conn = DatabaseConnection.getConnection();

		try (PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM prof_details WHERE User_Name = ? AND email_id = ? AND password = ?");) {
			pstmt.setString(1, User_Name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);

			ResultSet rs = pstmt.executeQuery();
			return rs.next();

		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
			return false;
		}
	}

	public boolean checkEmailAvailability(String email) {
		String query = "SELECT email_id FROM user_details WHERE email_id = ?";
		return existsByField(query, email);
	}

	public boolean checkusernameAvailability(String User_Name) {
		String query = "SELECT User_Name FROM user_details WHERE User_Name = ?";
		return existsByField(query, User_Name);

	}

	public boolean checkProfusernameAvailability(String User_Name) {
		String query = "SELECT User_Name FROM prof_details WHERE User_Name = ?";
		return existsByField(query, User_Name);
	}

	public boolean checkprofnameAvailability(String email) {
		String query = "SELECT email_id FROM prof_details WHERE email_id = ?";
		return existsByField(query, email);
	}

	private boolean existsByField(String query, String value) {
		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, value);
			try (ResultSet rs = pst.executeQuery()) {
				return rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void admin_regis_prof() {

		System.out.print("Enter Professor email_id: ");
		String email  = sc.nextLine();
		

		System.out.print("Enter Professor User_name: ");
		String profName = sc.nextLine();

		System.out.print("Set the Password: ");
		String password = sc.nextLine();

		Connection conn = DatabaseConnection.getConnection();
		// String query = "INSERT INTO admin_regis_prof (Prof_ID, Prof_Name,
		// Course_Handled) VALUES (?, ?, ?)";
		try {
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO prof_details (User_Name,email_id, password) VALUES (?, ?, ?)");


			pstmt.setString(2, email);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
			conn.commit();

			System.out.println("Professor registered successfully!");

		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());

		}

	}

	private static void admin_regis_user() {

		System.out.print("Enter User Name: ");
		String userName = sc.nextLine();

		System.out.print("Enter User Email: ");
		String userEmail = sc.nextLine();

		System.out.print("Enter the Password : ");
		String Password = sc.nextLine();

		Connection conn = DatabaseConnection.getConnection();
		// String query = "INSERT INTO admin_regis_user(User_ID, User_Name, Email,
		// Course_Enrolled) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO user_details(User_Name,email_id, password) VALUES (?, ?, ?)");

			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			pstmt.setString(3, Password);
			pstmt.executeUpdate();
			conn.commit();

			System.out.println("User registered successfully!");

		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}

	}

	private static void deleteCourse(Scanner sc) {
		System.out.print("Enter the Course ID to delete: ");
		int courseId = sc.nextInt();
		sc.nextLine();
		Connection conn = DatabaseConnection.getConnection();

		try {
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Course WHERE Course_ID = ?");

			pstmt.setInt(1, courseId);
			conn.commit();
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {

				System.out.println("Course deleted successfully!");
			} else {
				System.out.println("Course not found.");
			}

		} catch (SQLException e) {
			System.err.println("Error deleting course: " + e.getMessage());
		}
	}

	private static void deleteUser(Scanner sc) {
		System.out.print("Enter the User ID to delete: ");
		int userId = sc.nextInt();
		Connection conn = DatabaseConnection.getConnection();

		try {
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM user_details WHERE id = ?");

			pstmt.setInt(1, userId);
			
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("User deleted successfully!");
				conn.commit();
			} else {
				System.out.println("User not found.");
			}

		} catch (SQLException e) {
			System.err.println("Error deleting user: " + e.getMessage());
		}
	}

	private static void viewUsers() {
		System.out.println("\nRegistered Users:");

		String stquery = "SELECT * FROM user_details";
		String pquery = "select * from prof_details";

		Connection conn = DatabaseConnection.getConnection();
		try (Statement stmt = conn.createStatement();
				Statement ptmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(stquery);
				ResultSet prs = ptmt.executeQuery(pquery);) {

			System.out.println("Students");
			while (rs.next()) {
				String name = rs.getString("User_Name");
				String email = rs.getString("Email_Id");

				// Display user data
				System.out.println("Username: " + name);
				System.out.println("Email: " + email);
				System.out.println("--------------------------");
			}

			System.out.println("Professors");
			while (prs.next()) {
				String name = prs.getString("User_Name");
				String email = prs.getString("Email_Id");

				// Display user data
				System.out.println("Username: " + name);
				System.out.println("Email: " + email);
				System.out.println("--------------------------");
			}

		} catch (SQLException e) {
			System.err.println("Error retrieving users: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void viewCourses() {
		System.out.println("\nAvailable Courses:");
		try {
			ResultSet rs = CourseDAO.getCourses();
			while (rs.next()) {
				System.out.println("Course ID: " + rs.getInt("course_id") + ", Name: " + rs.getString("course_name")
						+ ", Description: " + rs.getString("course_desp"));
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving courses: " + e.getMessage());
		}
	}

	private static void registerCourse(Scanner sc) {
		try {

			System.out.print("Enter Course Name: ");
			String courseName = sc.nextLine();

			System.out.print("Enter Course Description: ");
			String courseDescp = sc.nextLine();
			
			System.out.println("Enter prof id");
			int profId = sc.nextInt();
			sc.nextLine();

			String query = "INSERT INTO Course (Course_Name, Course_Desp, professor_id) VALUES (?, ?,?)";
			Connection conn = DatabaseConnection.getConnection();
			try (PreparedStatement pstmt = conn.prepareStatement(query)) {

				pstmt.setString(1, courseName);
				pstmt.setString(2, courseDescp);
				pstmt.setInt(3, profId);

				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Course registered successfully!");
					conn.commit();

				} else {
					System.out.println("Failed to register the course. Please try again.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private class CourseDAO {
		public static ResultSet getCourses() throws SQLException {
			String query = "SELECT * FROM Course";
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(query);
		}
	}

	private static void enrollForCourse(Scanner sc) {
		String fetchCoursesQuery = "SELECT Course_ID, Course_Name FROM Course";

		Connection conn = DatabaseConnection.getConnection();
		try (
				PreparedStatement pstmt = conn.prepareStatement(fetchCoursesQuery);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int courseId = rs.getInt("Course_ID");
				String courseName = rs.getString("Course_Name");
				System.out.println(courseId + ": " + courseName);
			}

			System.out.print("Enter the Course ID to enroll in: ");
			int course_id = sc.nextInt();

			System.out.print("Enter your registered Email ID: ");
			String Email_id = sc.next();

			String enrollQuery = "INSERT INTO Enroll_details (Course_ID, Email_id) VALUES (?, ?)";
			try (PreparedStatement enrollStmt = conn.prepareStatement(enrollQuery)) {
				enrollStmt.setInt(1, course_id);
				enrollStmt.setString(2, Email_id);
				enrollStmt.executeUpdate();
				conn.commit();

				System.out.println("Successfully enrolled in the course!");
			}

		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void viewEnrolledCourses(Scanner sc, String Email_id) {
		System.out.print("Enter your registered Email ID: ");
		String inputEmail = sc.next();

		String query = "SELECT c.Course_ID, c.Course_Name, c.course_desp " + "FROM Enroll_details e "
				+ "JOIN course c ON e.Course_ID = c.Course_ID " + "WHERE e.Email_id = ?";

		Connection conn = DatabaseConnection.getConnection();
		try (
				PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setString(1, inputEmail);

			try (ResultSet rs = pstmt.executeQuery()) {
				System.out.println("Enrolled Courses:");
				boolean hasCourses = false;

				while (rs.next()) {
					hasCourses = true;
					int courseId = rs.getInt("Course_ID");
					String courseName = rs.getString("Course_Name");
					String description = rs.getString("course_desp");

					System.out.println("Course ID: " + courseId);
					System.out.println("Course Name: " + courseName);
					System.out.println("Description: " + description);
					System.out.println("----------------------------");
				}

				if (!hasCourses) {
					System.out.println("You are not enrolled in any courses.");
				}
			}

		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
	}

}
