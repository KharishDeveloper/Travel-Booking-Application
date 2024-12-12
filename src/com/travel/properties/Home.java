package com.travel.properties;

import java.io.IOException;
import java.sql.SQLException;

import com.travel.logic.ApplicationClosed;
import com.travel.logic.UserServiceLoginLogic;
import com.travel.logic.UserServiceRegisterLogic;

public class Home {

	public static void HomePage() throws NullPointerException, SQLException, IOException {
		System.out.println("select an option :");
		System.out.println("1.New user registration \t 2.Login account \n 3.Exit ");
		int UserChoice;
		UserChoice = Constants.sc.nextInt();
		switch (UserChoice) {
		case 1:
			UserServiceRegisterLogic.UserService();//register
		case 2:
			UserServiceLoginLogic.pwd();//login
			break;
		case 3:
			ApplicationClosed.ClosingProperty();//exit
			break;
		default:
			System.out.println("select the valid option !!!");
			System.out.println(" ");
			Home.HomePage();
//			break;
		}
	}
}
