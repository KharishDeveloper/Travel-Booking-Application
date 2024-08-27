package com.travel.saveDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.entity.UserServiceRegister;
import com.travel.properties.Constants;


public class UserServiceRegisterDB {

	public static boolean RegisterUserChecking(Connection con) throws SQLException, NullPointerException, IOException {
		String Query = "select mobile from users_data where mobile=?;";
		PreparedStatement ps = con.prepareStatement(Query);
		ps.setString(1, UserServiceRegister.mobileNumber);
		ResultSet Result = ps.executeQuery();
		if (Result.next()) {
			if (Result.getString(1).equals(UserServiceRegister.mobileNumber)) {
				System.out.println("you are already a registered user");
				System.out.println("just sign-in");
				return true;
			}
		}
		return false;
	}
	
	public static int InsertData(Connection con) throws SQLException {
		String Query1 = "insert into users_data(mobile,email,password,firstName,lastName,fullName) values(?,?,?,?,?,?);";
		PreparedStatement ps1 = Constants.DoConnect().prepareStatement(Query1);
		ps1.setString(1, UserServiceRegister.mobileNumber);
		ps1.setString(2, UserServiceRegister.Mail);
		ps1.setString(3, UserServiceRegister.password);
		ps1.setString(4, UserServiceRegister.FirstName);
		ps1.setString(5, UserServiceRegister.LastName);
		ps1.setString(6, UserServiceRegister.FullName);
		int update = ps1.executeUpdate();
		return update;

	}
}
