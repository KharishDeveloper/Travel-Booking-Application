package haritravels;

import java.io.*;
import java.sql.*;
//import java.util.*;

public class Sign {
	static String MobileNumber;

	public static void sign() throws NullPointerException, IOException, SQLException {
		String Password;
//		System.out.println("hello");
		System.out.println("Enter the mobile number :");
		MobileNumber = Constants.sc.next();
		System.out.println("Enter the password :");
		Password = Constants.sc.next();
		Connection connection = Constants.GetConnection();
		String sql = "select mobile from users_data where mobile=?;";
		PreparedStatement ps1 = connection.prepareStatement(sql);
		ps1.setString(1, MobileNumber);
		ResultSet result = ps1.executeQuery();
//			System.out.println("welcome to number verification");
		if (result.next()) {
//				System.out.println("you entered a valid mob. number !!!");
//			String GettingMobileNumber = result.getString(1);
//				System.out.println(string +" first data");
//				System.out.println("your password is available below");
//				System.out.println(string);
//			System.out.println("welcome to passowrd verification");
			String Query = "select password from users_data where password=?;";
			PreparedStatement ps2 = connection.prepareStatement(Query);
			ps2.setString(1, Password);
			ResultSet result1 = ps2.executeQuery();
//					System.out.println("outer loop");
			if (result1.next()) {
				String StoredPassword = result1.getString(1);
//						System.out.println(p);
				if (StoredPassword.contentEquals(Password)) {
//						System.out.println("password verified");
//					System.out.println("checking");
//					System.out.println("welcome");
//					System.out.println("failedcount :" + Constants.failedcount);
					System.out.println("you have successfully logged in to your haritravels account !!!");
					System.out.println(" ");
					System.out.println("you have book the buses from now !!!");
					Login.login_details();
				} else {
//					System.out.println("checking");
//					System.out.println("enter correct one !!!");
					System.out.println("invalid details was entered !!!");
					Constants.failedcount--;
					System.out.println("failedcount :" + --(Constants.failedcount) + "\t " + "default count is 3 ");
					System.out.println("sign-in the account with valid credentials !!");
					System.out.println("1. Reset the password");
					System.out.println("2. Continue to login");
					int PasswordUserChoice = Constants.sc.nextInt();
					switch (PasswordUserChoice) {
					case 1:
						Change.forgot();
						sign();
						break;

					// forgot password...
					case 2:

						sign();
						break;

					}

				}
			}

			else {
				if (Constants.failedcount == 0) {
					System.out.println("repeated login attempts");
					System.out.println("account blocked");
					System.out.println("wait 24 hrs for accessing account");
				} else {
					System.out.println("invalid details was entered !!!");
//					System.out.println("failedcount :" + --(Constants.failedcount) + "\t " + "default count is 3 ");
					Constants.failedcount--;
					System.out.println("sign-in the account with valid credentials !!");
					System.out.println("1. Reset the password");
					System.out.println("2. Continue to login");
					int PasswordUserChoice = Constants.sc.nextInt();
					switch (PasswordUserChoice) {
					case 1:
						Change.forgot();
						sign();
						break;

					// forgot password...
					case 2:

						sign();
						break;
					}
				}
//						}
//						catch(Exception e) {
//							System.out.println("no element");
//						}
			}
		} else {
			System.out.println("your details was not matched on our server !!");
			System.out.println("you must register first !!!");

			Menu.menu();
		}
	}

//		private static Connection getting() throws SQLException {
//			Connection connection = Connection_travels.GetConnection();
//			return connection;
//		}

}
