package com.travel.entity;

import com.travel.properties.Constants;

public class UserServiceLogin {

	public static String MobileNumber, Password;

	public static void TravelLogin() {
		System.out.println("Enter the mobile number :");
		MobileNumber = Constants.sc.next();
		System.out.println("Enter the password :");
		Password = Constants.sc.next();
	}
}
