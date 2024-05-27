package haritravels;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Constants {
	public static final File Logo = new File("C:\\Users\\haris\\OneDrive\\Desktop\\file folders\\logo.txt");
	public static int failedcount = 3;
	public static Scanner sc = new Scanner(System.in);

	public static Connection GetConnection() throws SQLException {
		Connection connection;
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/haritravel_withforeign", "root",
				"Harish@123");
		return connection;
	}
}