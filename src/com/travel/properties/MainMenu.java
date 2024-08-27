package com.travel.properties;

import java.sql.SQLException;

import com.travel.logic.UserFeatureTravelStatus;
import com.travel.logic.UserJourneyRegister;
import com.travel.logic.UserServiceLogout;
import com.travel.logic.UserServiceTravelTickets;
import com.travel.logic.UserServiceUpdatePassword;
import com.travel.logic.UserserviceReschedule;

public class MainMenu {

	public static void Main() throws SQLException {
		System.out.println(
				"1.plan the journey \t 2.get the tickets\n 3. reschedule \t 4. change the password \n 5. check the travelstatus \n 6. logout");
		int Choice = Constants.sc.nextInt();
		switch (Choice) {
		case 1:
			UserJourneyRegister.registartion();
			break;
		case 2:
			UserServiceTravelTickets.GetTicketInformation();
			break;
		case 3:
			UserserviceReschedule.Reschedule();
			break;
		case 4:
			UserServiceUpdatePassword.UpdatePasswordAfterLogin();
			break;
		case 5:
			UserFeatureTravelStatus.FeatureImplement();
			break;
		case 6:
			UserServiceLogout.Logout();
			break;
		default:
			System.out.println("select the certain one ");
			Main();
			break;
		}

	}
}
