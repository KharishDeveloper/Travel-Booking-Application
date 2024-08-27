package com.travel.logic;

import java.sql.SQLException;

import com.travel.entity.UserServiceLogin;
import com.travel.properties.Constants;
import com.travel.properties.MainMenu;
import com.travel.saveDB.UserServiceLoginDB;

public class UserServiceLoginLogic {

	public static void pwd() throws SQLException {
		UserServiceLogin.TravelLogin();
		boolean checkMobAndPwd = UserServiceLoginDB.CheckMobAndPwd(Constants.DoConnect(), UserServiceLogin.MobileNumber,
				UserServiceLogin.Password);
		if (checkMobAndPwd) {

			System.out.println("you have successfully logged in to your haritravels account !!!");
			System.out.println(" ");
			System.out.println("you have book the buses from now !!!");
			MainMenu.Main();
		} else {
			System.out.println("invalid details was entered !!!");
			Constants.FailedCount--;
			System.out.println("failedcount :" + --(Constants.FailedCount) + "\t " + "default count is 3 ");
			System.out.println("sign-in the account with valid credentials !!");
			System.out.println("1. Reset the password");
			System.out.println("2. Continue to login");
			int TakingValue = Constants.sc.nextInt();
			switch (TakingValue) {
			case 1:
				UserServiceUpdatePassword.FogotPassword();
				break;
			case 2:
				UserServiceLoginLogic.pwd();
				break;
			default:
				System.out.println("choose valid option !!!");
				break;
			}
		}
	}
}
