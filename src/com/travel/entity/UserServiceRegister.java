package com.travel.entity;

import com.travel.properties.Constants;

public class UserServiceRegister {

	public static String FirstName, LastName, FullName, Mail, mobileNumber, password;
	public static int Gender;

	public static void Register() {

		System.out.println("New user registration ");
		System.out.println("Enter the first name :");
		FirstName = Constants.sc.next();
		System.out.println("Enter the last name :");
		LastName = Constants.sc.next();
		FullName = (FirstName + " " + LastName);
		System.out.println("Your name is " + FullName);
		System.out.println("Select your gender :");
		System.out.println("1.Male\n 2.Female \n 3. Others");
		Gender = Constants.sc.nextInt();
		System.out.println("Enter your Gmail-id :");
		Mail = Constants.sc.next();
		System.out.println("Enter your mobile number :");
		mobileNumber = Constants.sc.next();
		System.out.println("Enter the password :");
		password = Constants.sc.next();
	}
}