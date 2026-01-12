package com.travel.logic;

import java.io.IOException;
import java.sql.SQLException;

import com.travel.entity.UserServiceLogin;
import com.travel.properties.Constants;
import com.travel.properties.Home;
import com.travel.properties.MainMenu;
import com.travel.saveDB.UserServiceLoginDB;

public class UserServiceLoginLogic {

	public static void pwd() throws SQLException, NullPointerException, IOException {
		UserServiceLogin.TravelLogin();
		boolean checkMobAndPwd=false;
		boolean CheckMailOrMob=(UserServiceLogin.MobileNumber.contains("@") && UserServiceLogin.MobileNumber.contains(".com"));
		if(CheckMailOrMob) {
		checkMobAndPwd = UserServiceLoginDB.CheckMailAndPwd(Constants.DoConnect(), UserServiceLogin.MobileNumber,
				UserServiceLogin.Password);
		}else {
			checkMobAndPwd = UserServiceLoginDB.CheckMobAndPwd(Constants.DoConnect(), UserServiceLogin.MobileNumber,
					UserServiceLogin.Password);
		}
		
		if (checkMobAndPwd) {

			System.out.println("you have successfully logged in to your haritravels account !!!");
			System.out.println(" ");
			System.out.println("you have book the buses from now !!!");
			MainMenu.Main();
		} else if (UserServiceLogin.MobileNumber.contains("+91")) {
			boolean checkMobAndPwdSpecial=UserServiceLoginDB.CheckMobAndPwdForSpecialAccounts(Constants.DoConnect(), UserServiceLogin.MobileNumber, UserServiceLogin.Password);
			if (checkMobAndPwdSpecial) {
				String retrieveRole=UserServiceLoginDB.GetRole(Constants.DoConnect(), UserServiceLogin.MobileNumber);
				// sql query to get the role based on mobile number for special access accounts
				System.out.println("WELCOME "+retrieveRole.toUpperCase());
			} else {
				System.out.println("you entered wrong password mr.admin");
				Home.HomePage();
			}
		}

		else {
			System.out.println("invalid details was entered !!!");
			Constants.FailedCount--;
			System.out.println("failedcount :" + (Constants.FailedCount) + "\t " + "default count is 3 ");
			System.out.println("sign-in the account with valid credentials !!");
			System.out.println("1. Reset the password");
			System.out.println("2. Continue to login");
			int InvalidCrentialValue = Constants.sc.nextInt();
			switch (InvalidCrentialValue) {
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
