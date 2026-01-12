package com.travel.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

public class Constants {

	public static int FailedCount = 3;
	public static String HTTSID;
	public static final File Logo = new File("C:\\Users\\haris\\OneDrive\\Desktop\\text files\\pravasa connect1.txt");
	public static Scanner sc = new Scanner(System.in);
	

	public static Connection DoConnect() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelapplication", "root",
					"Harish@123");
		} catch (SQLException e) {
			System.out.println("fail to establish the database connection");
		}
		return con;

	}

	public static void GetLogoInformation() throws IOException, NullPointerException, SQLException {
		FileInputStream fis = new FileInputStream(Logo);
		int reader;
		while ((reader = fis.read()) != -1) {
			System.out.print((char) reader);
		}
		System.out.println();
		fis.close();
		Home.HomePage();

	}

	public static String GenerateHttsID() {
		// generate HTTS
		int length = 4;
		Supplier<String> su = () -> {
//
			String data = "1234567890";
			Random ran = new Random();
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= length; i++) {
				int index = ran.nextInt(data.length());
				sb.append(data.charAt(index));
			}
			return sb.toString();
		};
		HTTSID = su.get();
		return HTTSID;
	}
}
