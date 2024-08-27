package com.travel.logic;

import java.io.IOException;
import java.sql.SQLException;

import com.travel.entity.UserServiceRegister;
import com.travel.properties.Constants;
import com.travel.saveDB.UserServiceRegisterDB;

public class UserServiceRegisterLogic {

	public static void UserService() throws NullPointerException, SQLException, IOException {
		UserServiceRegister.Register();

		switch (UserServiceRegister.Gender) {
		case 1:
			System.out.println("Male");
			break;
		case 2:
			System.out.println("Female");
			break;
		case 3:
			System.out.println("Others");
			break;
		default:
			while (UserServiceRegister.Gender > 4) {
				System.out.println("Select the valid gender type !");
				System.out.println("Enter again");
				UserServiceRegister.Gender = Constants.sc.nextInt();
			}
		}
		while (!((UserServiceRegister.Mail.contains("gmail.com")))) {
			System.out.println("Enter the valid mail address");
			UserServiceRegister.Mail = Constants.sc.next();
		}
		while (UserServiceRegister.mobileNumber.length() != 10) {
			System.out.println("enter the valid mobile number !!!");
			UserServiceRegister.mobileNumber = Constants.sc.next();
		}
		UserServiceRegister.password = PasswordLogic.PasswordMaintainingLogic(UserServiceRegister.password);
		System.out.println("You have successfully completed the registration !!!");
		System.out.println(" ");
		boolean registerUserChecking = UserServiceRegisterDB.RegisterUserChecking(Constants.DoConnect());
		if (!registerUserChecking) {
			UserServiceRegisterDB.InsertData(Constants.DoConnect());
			System.out.println("You have login to the account");
			System.out.println(" ");
		}

	}
}
