package com.travel.saveDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatepasswordDB {

	public static boolean CheckMobileNumber(Connection con, String number) throws SQLException {

		String sql = "select mobile from users_data where mobile=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, number);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			return true;
		}
		return false;
	}

	public static boolean GetOldPWD(Connection con, String mobile, String pwd) throws SQLException {
		String sql = "select password from users_data where mobile=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, mobile);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			if (set.getString(1).equals(pwd)) {
				return false;
			}
		}
		return true;
	}

	public static void PasswordUpdate(Connection con, String mobile, String pwd) throws SQLException {
		String sql = "update users_data set password=? where mobile=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, pwd);
		stmt.setString(2, mobile);
		stmt.executeUpdate();
	}
}
