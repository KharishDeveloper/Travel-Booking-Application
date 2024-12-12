package com.travel.logic;

import java.sql.SQLException;

import com.travel.properties.Constants;
import com.travel.properties.MainMenu;
import com.travel.saveDB.GetTicketsDB;
import com.travel.saveDB.TravelStatusFeatureDB;

public class UserServiceTravelTickets {
	static String id;

	public static void GetTicketInformation() throws SQLException {
		/*
		 * 1. get the travel status and take HTTS ID
		 * 
		 * 2. Display the information like source, destination, fare amount, bookings
		 * count and passenger information
		 */

		System.out.println("Enter the HTTS-ID : ");
		id = Constants.sc.next();
		String checkID = TravelStatusFeatureDB.GetTravelDate(Constants.DoConnect(), UserServiceTravelTickets.id);
		if(checkID==null) {
			System.out.println("not found any details !!!");
			MainMenu.Main();
		}
		else {
			
		UserFeatureTravelStatus.TravelStatus(checkID);
		TravelStatusFeatureDB.UpdateTravelStatus(Constants.DoConnect(), checkID, UserServiceTravelTickets.id);
		GetTicketsDB.GetInformationFromDetails(Constants.DoConnect(), id);
		if(UserFeatureTravelStatus.TravelStatus.equalsIgnoreCase("completed the travel")) {
			System.out.println("no bookings or your travel was completed !!!");
			MainMenu.Main();
		}
		else {
			GetTicketsDB.GetPassengerInformation(Constants.DoConnect(), id);
			MainMenu.Main();
//			break;
			
		}
		}
		
		
	}
}
