package com.travel.logic;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.travel.properties.Constants;
import com.travel.properties.MainMenu;
import com.travel.saveDB.TravelStatusFeatureDB;

public class UserFeatureTravelStatus {

	static String TravelStatus;

	public static void FeatureImplement() throws SQLException {

		try {

			String checkID = TravelStatusFeatureDB.GetTravelDate(Constants.DoConnect(), UserServiceTravelTickets.id);
			TravelStatus(checkID);
			TravelStatusFeatureDB.UpdateTravelStatus(Constants.DoConnect(), checkID, UserServiceTravelTickets.id);
			MainMenu.Main();
		} catch (Exception q) {
			System.out.println("you are an invalid ID and try again");
		}
		
	}

	public static String TravelStatus(String Date) {
//		System.out.println("Date : "+Date);
		LocalDate now = LocalDate.now();
		LocalDate parse = LocalDate.parse(Date);
		long Days = ChronoUnit.DAYS.between(now, parse);
		if (Days >= 2) {
			TravelStatus = "Travel within " + Days + " days";

		} else if (Days == 0) {
			TravelStatus = "Today is your travel, ready to travel / travelling";
		} else if (Days == (-1)) {
			TravelStatus = "travelling / completed";
		} else if (Days == (-2)) {
			TravelStatus = "completed the travel";
		} else if (Days <= (-3)) {
			TravelStatus = "completed the travel";
		} else if (Days == 1) {
			TravelStatus = "tomorrow is your travel date";
		}
		return TravelStatus;
	}
}
