package com.travel.logic;

import java.sql.SQLException;

import com.travel.entity.UserServiceLogin;
import com.travel.properties.Constants;
import com.travel.properties.MainMenu;
import com.travel.saveDB.UpdatepasswordDB;

public class UserServiceUpdatePassword {

	public static void FogotPassword() throws SQLException {

		System.out.println("Enter the mobile number : ");
		String MobileNumber = Constants.sc.next();
		// write a d b connect call to check the mob is registered or not
		boolean checkMobileNumber = UpdatepasswordDB.CheckMobileNumber(Constants.DoConnect(), MobileNumber);
		if (checkMobileNumber) {
			String OTP = Constants.GenerateHttsID();
			System.out.println("Your OTP is : " + OTP);
			System.out.println("Enter the OTP : ");
			String enter = Constants.sc.next();
			if (OTP.equals(enter)) {
				System.out.println("Enter the new password : ");
				String newpwd = Constants.sc.next();
				System.out.println("Enter the confirm password : ");
				String confirmpwd = Constants.sc.next();
				if (newpwd.equals(confirmpwd)) {
					// retrieve old pwd and checks with new pwd
					boolean oldPWD = UpdatepasswordDB.GetOldPWD(Constants.DoConnect(), MobileNumber, confirmpwd);
					if (oldPWD) {
						UpdatepasswordDB.PasswordUpdate(Constants.DoConnect(), MobileNumber, confirmpwd);
						System.out.println("updated successfully !!!");

					} else {
						System.out.println("your new and old password must not be same !!!");
					}

				} else {
					System.out.println("your new and confirm password must be same !!!");
				}
			}
			System.out.println("mismatched the OTP");
			System.out.println("please try again later and enter correct OTP");
		} else {
			System.out.println("you are not a registered user ");
			System.out.println("please register first !!!");
		}
	}

	public static void UpdatePasswordAfterLogin() throws SQLException {

		boolean number = UpdatepasswordDB.CheckMobileNumber(Constants.DoConnect(), UserServiceLogin.MobileNumber);
		if (number) {
			String OTP = Constants.GenerateHttsID();
			System.out.println(OTP);
			System.out.println("Enter the OTP : ");
			String enter = Constants.sc.next();
			if (OTP.equals(enter)) {
				System.out.println("Enter the new pwd : ");
				String newpwd = Constants.sc.next();
				System.out.println("Enter the confirm pwd : ");
				String confirmpwd = Constants.sc.next();
				if (newpwd.equals(confirmpwd)) {
					boolean oldPWD = UpdatepasswordDB.GetOldPWD(Constants.DoConnect(), UserServiceLogin.MobileNumber,
							confirmpwd);
					if (oldPWD) {
						UpdatepasswordDB.PasswordUpdate(Constants.DoConnect(), UserServiceLogin.MobileNumber,
								confirmpwd);
						System.out.println("updated successfully !!!");
						MainMenu.Main();

					} else {
						System.out.println("your old and new password must not be same !!!");
					}
				} else {
					System.out.println("your new and confirm password must be same !!!");
				}
			} else {
				System.out.println("you entered wrong OTP");
			}
			// generate OTP and verify
			// take new pwds and must not be same as previous pwd
		}
	}
}
