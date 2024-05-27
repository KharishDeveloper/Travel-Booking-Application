package haritravels;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Supplier;

public class Change extends Sign {

	static String HTTSId;

	public static void forgot() throws SQLException, NullPointerException, IOException {
		System.out.println("enter the mobile number :");
		String MobileNumber = Constants.sc.next();
		Connection Connection = Constants.GetConnection();
		String query = "select mobile from users_data where mobile=?;";
		PreparedStatement ps = Connection.prepareStatement(query);
		ps.setString(1, MobileNumber);
		ResultSet Result = ps.executeQuery();
		if (Result.next()) {
			int length = 6;
			Supplier<String> OTPGenerator = () -> {

				String data = "0123456789";
				Random ran = new Random();
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i <= length; i++) {
					int val = data.length();// data length is 3...
					int index = ran.nextInt(val);// 1,3,2,1,3,2
					// System.out.println(index);
					sb.append(data.charAt(index));
				}
				return sb.toString();
			};
//		String password;
//		Sign getps = new Sign();
//		password = getps.password_for_login;
//		System.out.println(password+"password getting");
			System.out.println("forgot password");
			String OTPGenerate = OTPGenerator.get();
			System.out.println("your otp is :" + OTPGenerate);
			System.out.println("enter your otp :");
			String otp = Constants.sc.next();
			System.out.println("entered otp is :" + otp);
			if (OTPGenerate.equals(otp)) {
				String query1 = "select password from users_data where mobile=?;";
				PreparedStatement ps1 = Connection.prepareStatement(query1);
				ps1.setString(1, MobileNumber);
				ResultSet resultSet = ps1.executeQuery();
				if (resultSet.next()) {
//					System.out.println("same passowrd !!!\n enter the new passowrd");
//					forg_pass = signn.next();
//					
				}
				String Password = resultSet.getString(1);
				System.out.println("enter your new password");
				String NewPassword = Constants.sc.next();
				while (Password.equals(NewPassword)) {
					System.out.println("old and new password must not be same !!!");
					System.out.println("Enter your password again :");
					NewPassword = Constants.sc.next();
				}
				System.out.println("enter your password again..");
				String ConfirmPassword = Constants.sc.next();
				if (NewPassword.equals(ConfirmPassword)) {

					System.out.println("successfully changed the password");
				} else {
					System.out.println("password mismatched");
					System.out.println("enter your password correctly");
					Change.forgot();

				}

				String query2 = "update users_data set password=? where mobile=?;";
				PreparedStatement ps2 = Connection.prepareStatement(query2);
				ps2.setString(1, ConfirmPassword);
				ps2.setString(2, MobileNumber);
				ps2.execute();
//				System.out.println("successfully changing the password");
			} else {
				System.out.println("otp mismatch");
				Change.forgot();
			}
		} else {
			System.out.println("you are not a existing user");
			System.out.println("create the account first");
			Menu.menu();
		}
//		return null;
	}

	public static void changing() throws SQLException, NullPointerException, IOException {
		String login = Sign.MobileNumber;
		System.out.println("enter your registered mobile number :");
		String MobileNumber = Constants.sc.next();
		if (login.contentEquals(MobileNumber)) {
			// mobile number is existed or not [done]
			String sql = "select mobile from details where mobile=?;";
			PreparedStatement ps = Constants.GetConnection().prepareStatement(sql);
			ps.setString(1, MobileNumber);
//		System.out.println("mob.num checking");
			ResultSet MobileSelectQuery = ps.executeQuery();
			if (MobileSelectQuery.next()) {
//			System.out.println("done the mob");
				System.out.println(MobileSelectQuery.getString(1));
				System.out.println("enter your password !!!");
				String Password = Constants.sc.next();
				System.out.println("enter the confirm password :");
				String ConfirmPassword = Constants.sc.next();
				String sql1 = "select password from users_data where mobile=?;";
				PreparedStatement ps1 = Constants.GetConnection().prepareStatement(sql1);
				ps1.setString(1, MobileNumber);
				ResultSet PasswordSelectQuery = ps1.executeQuery();
//			System.out.println("checking pass");
				if (PasswordSelectQuery.next()) {
//				System.out.println("done the pass");
					String GetPassword = PasswordSelectQuery.getString(1);
//				System.out.println(get +"\t password");
//				System.out.println(get.equals(pass)+"while");
					while (GetPassword.equals(Password)) {
						System.out.println("you entered old password !!!");
						System.out.println("enter another strong passowrd");
						Password = Constants.sc.next();
						System.out.println("enter the confirm password :");
						ConfirmPassword = Constants.sc.next();
					}
					if (Password.equals(ConfirmPassword)) {
						String sql2 = "update users_data set password=? where mobile=?;";
						PreparedStatement ps2 = Constants.GetConnection().prepareStatement(sql2);
						ps2.setString(1, ConfirmPassword);
						ps2.setString(2, MobileNumber);
						ps2.executeUpdate();
						System.out.println("successfully updated the password");
						Login.login_details();

					}

				}

				// retrieve existing password
				// update the password
			} else {
				System.out.println("not a valid mobile number !!!");
				Menu.menu();
			}
		} else {
			System.out.println("you cannot change the passowrd to this mobile number !!!");
			System.out.println("if you change just sign-in to this number !! ");
			Sign.sign();
		}
	}

	public static String GenerateHttsID() {
		// generate HTTS
		int length = 4;
		Supplier<String> su = () -> {

			String data = "1234567890";
			Random ran = new Random();
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= length; i++) {
				int val = data.length();// data length is 3...
				int index = ran.nextInt(val);// 1,3,2,1,3,2
				// System.out.println(index);
				sb.append(data.charAt(index));
			}
			return sb.toString();
		};
		HTTSId = su.get();
//		System.out.println("generated id is[HTTSId] :" + HTTSId);
		return HTTSId;
	}
}