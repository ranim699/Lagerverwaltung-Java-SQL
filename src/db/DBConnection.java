package db;

import java.sql.*;

public class DBConnection {
	// Enter your own Oracle database here
	private final static String driver = "oracle.jdbc.driver.OracleDriver";
	private final static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private final static String login = "YOUR_USERNAME";
	private final static String password = "YOUR_PASSWORD";
	private static Connection instanceConnection = null;

	private DBConnection() {

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}

		try {
			instanceConnection = DriverManager.getConnection(url, login, password);

			System.out.println("Connected to the database successfully!");

		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public synchronized static Connection getInstance() {
		if (instanceConnection == null) {
			new DBConnection();
		}

		return instanceConnection;
	}

	public static boolean disconnect() {
		try {
			instanceConnection.close();
			instanceConnection = null;
			System.out.println("Disconnected from the database successfully!");
			return true;
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return false;
	}

}
