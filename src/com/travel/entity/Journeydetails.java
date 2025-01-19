package com.travel.entity;

import java.sql.SQLException;
import java.time.LocalDate;

import com.travel.properties.Constants;

public class Journeydetails {

	public static String Date, Source, Destination;
	public static int Bookings;

	public static void mappingJouneyDetails() throws SQLException{

		System.out.println("Today's date is :" + LocalDate.now());
		System.out.println("enter the details of your journey");
		Constants.sc.nextLine();
		System.out.println("enter the source :");
		Source = Constants.sc.nextLine();
		System.out.println("enter the destination :");
		Destination = Constants.sc.nextLine();
		System.out.println("Enter the date :[YYYY-MM-DD]");
		Date = Constants.sc.nextLine();
		System.out.println("how many tickets you want to book in HARITRAVELS");
		Bookings = Constants.sc.nextInt();

	}
}
