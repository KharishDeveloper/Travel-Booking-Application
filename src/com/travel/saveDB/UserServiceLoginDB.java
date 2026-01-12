package com.travel.saveDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceLoginDB {

	public static boolean CheckMailAndPwd(Connection con, String mobile, String password) throws SQLException {
		String sql = "select password from users_data where email=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, mobile);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			if (set.getString(1).equals(password)) {
				System.out.println("login successfull");
				return true;
			}
		}
		return false;
	}

	public static boolean CheckMobAndPwd(Connection con, String mobile, String password) throws SQLException {
		String sql = "select password from users_data where mobile=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, mobile);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			if (set.getString(1).equals(password)) {
				System.out.println("login successfull");
				return true;
			}
		}
		return false;
	}

	public static boolean CheckMobAndPwdForSpecialAccounts(Connection con, String mobile, String password)
			throws SQLException {
		String sql = "select password from specialaccounts where MobileNumber=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, mobile);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			if (set.getString(1).equals(password)) {
				System.out.println("login successfull");
				return true;
			}
		}
//		else {
//			System.out.println("invalid mobile number !!!");
//		}
		return false;
	}

	public static String GetRole(Connection con, String mobile) throws SQLException {
		String sql = "select ROLE from specialaccounts where MobileNumber=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, mobile);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			return set.getString(1);
		}
		return null;
	}
}
