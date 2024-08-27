package com.travel.logic;

import java.io.IOException;
import java.sql.SQLException;

import com.travel.properties.Constants;

public class UserServiceLogout {

	public static void Logout() {
		
		boolean isLoggedIn = true;
		if (isLoggedIn == true) {
			isLoggedIn = false;
			try {
				Constants.GetLogoInformation();
				System.out.println("logout successful");
			} catch (NullPointerException | IOException | SQLException e) {
				e.printStackTrace();
				System.out.println("error !!!");
			}
		} else {
			System.out.println("error !!!");
		}
	}
}
