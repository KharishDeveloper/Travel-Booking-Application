package com.travel.properties;

import java.sql.SQLException;

import com.travel.logic.UserFeatureTravelStatus;
import com.travel.logic.UserJourneyRegister;
import com.travel.logic.UserServiceLogout;
import com.travel.logic.UserServiceTicketCancellation;
import com.travel.logic.UserServiceTravelTickets;
import com.travel.logic.UserServiceUpdatePassword;
import com.travel.logic.UserserviceReschedule;

public class MainMenu {

	public static void Main() throws SQLException {
		System.out.println(
				"1.plan the journey \t 2.get the tickets\n 3. reschedule \t 4. change the password \n 5. check the travelstatus \n 6. Cancel Ticket \n 7. logout");
		int Choice = Constants.sc.nextInt();
		switch (Choice) {
		case 1:
			UserJourneyRegister.registartion();//plan journey
			break;
		case 2:
			UserServiceTravelTickets.GetTicketInformation();//get tickets
			break;
		case 3:
			UserserviceReschedule.Reschedule();//reschedule ticket
			break;
		case 4:
			UserServiceUpdatePassword.UpdatePasswordAfterLogin();//update password after login
			break;
		case 5:
			UserFeatureTravelStatus.FeatureImplement();//travel status
			break;
		case 6:
			// canceling ticket
			UserServiceTicketCancellation.CancelTicket();//cancel ticket
			break;
		case 7:
			UserServiceLogout.Logout();//logout
			break;
		default:
			System.out.println("select the certain one ");
			Main();
			break;
		}

	}
}
