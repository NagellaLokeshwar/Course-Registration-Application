package db;


import java.sql.*;


public class DatabaseConnection {

    private static String url="jdbc:sqlite:app.db";
    private static String username="";
    private static String password="";

    private static Connection conn = null;
    static {
        try {
        	Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return conn;
    }

}
