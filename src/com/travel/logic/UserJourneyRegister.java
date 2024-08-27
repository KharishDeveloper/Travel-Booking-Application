package com.travel.logic;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.travel.properties.Constants;
import com.travel.properties.MainMenu;
import com.travel.saveDB.BusServiceDB;

public class UserJourneyRegister {

	public static String Date, name, number;
	public static int RemainingSeats, FareAmount, Bookings, TaxAmount, age, orgBook;

	public static void registartion() throws SQLException {

		System.out.println("today date is :" + LocalDate.now());
		System.out.println("enter the details of your journey");
		System.out.println("Enter the date :[YYYY-MM-DD]");
		Date = Constants.sc.next();
		LocalDate TravelDate = LocalDate.parse(Date);
		long Days = ChronoUnit.DAYS.between(LocalDate.now(), TravelDate);
		System.out.println(Days);
		while (Days < 1) {
			System.out.println("please enter the valid date");
			Date = Constants.sc.next();
			TravelDate = LocalDate.parse(Date);
			Days = ChronoUnit.DAYS.between(LocalDate.now(), TravelDate);

		}
		System.out.println("your journey date is :" + TravelDate);
		DayOfWeek WeekValue = TravelDate.getDayOfWeek();
		System.out.println("here are the available buses");
		BusServiceDB.GetBusDetails();
		System.out.println("enter the source :");
		String Source = Constants.sc.next();
		System.out.println("enter the destination :");
		String Destination = Constants.sc.next();
		System.out.println("how many tickets you want to book in HARITRAVELS");
		Bookings = Constants.sc.nextInt();
		orgBook = Bookings * 3;
		BusServiceDB.GetBusBookingDetails(Source, Destination);
		if (Bookings <= 5) {
			System.out.println("getting availability");
			int AvailableSeats = BusServiceDB.seats;
			if ((AvailableSeats <= Bookings)) {// 5->seats

				System.out.println("not availabile to book the tickets");
				System.out.println("we could not process the booking on this particular ticket number !!!");
				System.out.println("application terminated !!!");
			} else {
				System.out.println("available");
				int ActualAmount = BusServiceDB.amount * Bookings;
				System.out.println("amount : " + BusServiceDB.amount);
				System.out.println("Ticket price :\t" + ActualAmount);
				TaxAmount = ActualAmount / 18;
				System.out.println("taxes :\t\t " + TaxAmount);
				FareAmount = ActualAmount + TaxAmount;
				RemainingSeats = BusServiceDB.seats - Bookings;
				if (WeekValue.getValue() > 5) {
					ActualAmount = (ActualAmount + 200) * Bookings;
					System.out.println("Total price :\t" + FareAmount);
					System.out.println("remaining seats :" + RemainingSeats);
					BusServiceDB.UpdateSeats(Constants.DoConnect(), RemainingSeats, Source, Destination);

				} else {
					System.out.println("Total price :\t" + FareAmount);// (fare_amount+(org_amount*booking)) +"
																		// /-
																		// "+"only");

				}

				Constants.HTTSID = Constants.GenerateHttsID();
			}
		} else {
			System.out.println("you are not allowed to book ");
			System.out.println("because this system support maximum 5 bookings");
			System.out.println("and re-try again");
//			UserJourneyRegister.registartion();
		}
		BusServiceDB.InsertDetails(Constants.DoConnect(), Bookings, Source, Destination, TaxAmount, FareAmount);
		BusServiceDB.UpdateSeats(Constants.DoConnect(), RemainingSeats, Source, Destination);
		System.out.println("booking value : " + Bookings);
		System.out.println("done the process");
		Booker();
	}

	public static void BookingPassengerInformation() throws SQLException {
		System.out.println("Enter the name : ");
		name = Constants.sc.next();
		System.out.println("Enter the Mobile number : ");
		number = Constants.sc.next();
		System.out.println("Enter the age : ");
		age = Constants.sc.nextInt();
	}

	public static void Booker() throws SQLException {
		switch (Bookings) {
		case 5:

			BookingPassengerInformation();
			BusServiceDB.InsertPassenger5(Constants.DoConnect(), Constants.HTTSID, name, number, age);
			System.out.println("end");
		case 4:

			BookingPassengerInformation();
			BusServiceDB.InsertPassenger4(Constants.DoConnect(), Constants.HTTSID, name, number, age);
			System.out.println("end");
		case 3:
			BookingPassengerInformation();
			BusServiceDB.InsertPassenger3(Constants.DoConnect(), Constants.HTTSID, name, number, age);
			System.out.println("end");
		case 2:
			BookingPassengerInformation();
			BusServiceDB.InsertPassenger2(Constants.DoConnect(), Constants.HTTSID, name, number, age);
			System.out.println("end");
		case 1:
			BookingPassengerInformation();
			BusServiceDB.InsertPassenger1(Constants.DoConnect(), Constants.HTTSID, name, number, age);
			System.out.println("end");
			MainMenu.Main();
			break;
		default:
			System.out.println("unfortunately, you are not allowed to save the details !!!");
			break;
		}
	}
}