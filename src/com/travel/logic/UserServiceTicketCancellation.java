package com.travel.logic;

import java.sql.Connection;
import java.sql.SQLException;

import com.travel.entity.UserServiceLogin;
import com.travel.properties.Constants;
import com.travel.properties.MainMenu;
import com.travel.saveDB.CancelTicketDB;
import com.travel.saveDB.GetTicketsDB;

public class UserServiceTicketCancellation {

	public static void CancelTicket() throws SQLException {
		System.out.println("Enter the id : ");
		UserServiceTravelTickets.id = Constants.sc.next();// take the HTTS id for retrieving

		Connection connect = Constants.DoConnect();
		boolean checkHTTSId = GetTicketsDB.CheckHTTSId(connect, UserServiceTravelTickets.id);
		if (checkHTTSId) {
			boolean checkCancelActivity = CancelTicketDB.CheckCancelActivity(connect, UserServiceTravelTickets.id,
					UserServiceLogin.MobileNumber);

			if (checkCancelActivity == true) {

				CancelTicketDB.GettingCancelTicketInformation(connect, UserServiceTravelTickets.id);
			} else {
				System.out.println("you don't have an activity to cancel the ticket");
				MainMenu.Main();
			}
		} else {
			System.out.println("your HTTSid was not matched to our data");
			System.out.println("please try again with correct id !!!");
			MainMenu.Main();
		}
	}
}
