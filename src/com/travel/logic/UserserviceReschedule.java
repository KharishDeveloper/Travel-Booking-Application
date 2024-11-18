package com.travel.logic;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.travel.properties.Constants;
import com.travel.properties.MainMenu;
import com.travel.saveDB.RescheduleServiceDB;
import com.travel.saveDB.TravelStatusFeatureDB;

public class UserserviceReschedule {
	static String id;
	static long days;

	public static void Reschedule() throws SQLException {
		System.out.println("Enter the HTTS_ID : ");
		id = Constants.sc.next();
		String getTravelDate = TravelStatusFeatureDB.GetTravelDate(Constants.DoConnect(), id);
		if (!(getTravelDate.equals(null))) {
			System.out.println("you want to reschedule the ticket");
			System.out.println("1. rescheule the ticket\n 2.Return to main menu");
			int Choice = Constants.sc.nextInt();
			switch (Choice) {
			case 1:
				RescheduleUpdate(getTravelDate);
				MainMenu.Main();
				break;
			case 2:
				MainMenu.Main();
				break;
			default:
				System.out.println("pass the correct value");
				MainMenu.Main();
				break;
			}
		} else {
			System.out.println("You don't have any upcoming travel date !!!");
		}
	}

	public static void RescheduleUpdate(String TravellerDate) throws SQLException {
		System.out.println(
				"You can only reschedule the ticket after 10 days from the date which date you choosed previously !!");
		System.out.println("Enter the date :[YYYY-MM-DD]");
		String Date = Constants.sc.next();
		LocalDate RescheduleDate = LocalDate.parse(Date);
		LocalDate PreviousTravelDate = LocalDate.parse(TravellerDate);
		days = ChronoUnit.DAYS.between(PreviousTravelDate, RescheduleDate);
		System.out.println(days);
		while (days <= 10) {
			System.out.println(days);
			System.out.println("you are not allowed to reschedule teh travel");
			System.out.println("please change the date !!!");
			Date = Constants.sc.next();
			RescheduleDate = LocalDate.parse(Date);
			days = ChronoUnit.DAYS.between(PreviousTravelDate, RescheduleDate);
			System.out.println(days);
		}
		System.out.println(days);
		System.out.println("Your travel date is : " + Date);
		RescheduleServiceDB.UpdateRescheduleInformation(Constants.DoConnect(), Date, id);
		System.out.println("updated successfully");

		// calculate the diff b/w already travel date and rescheduling travel date
		// if the diff is more than 10 then update it
		// else choose another date
	}
}
