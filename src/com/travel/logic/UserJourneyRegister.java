package com.travel.logic;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.travel.entity.Journeydetails;
import com.travel.properties.Constants;
import com.travel.properties.MainMenu;
import com.travel.saveDB.BusServiceDB;

public class UserJourneyRegister {

	public static String name, number;
	public static int RemainingSeats, FareAmount, TaxAmount, age, orgBook, BusNumber;

	public static void registartion() throws SQLException {

//		System.out.println("today date is :" + LocalDate.now());
//		System.out.println("enter the details of your journey");
//		System.out.println("Enter the date :[YYYY-MM-DD]");
//		Date = Constants.sc.next();
		Journeydetails.mappingJouneyDetails();
		LocalDate TravelDate = LocalDate.parse(Journeydetails.Date);
		long Days = ChronoUnit.DAYS.between(LocalDate.now(), TravelDate);
//		System.out.println(Days);
		while (Days < 1) {
			System.out.println("please enter the valid date");
			Journeydetails.Date = Constants.sc.next();
			TravelDate = LocalDate.parse(Journeydetails.Date);
			Days = ChronoUnit.DAYS.between(LocalDate.now(), TravelDate);

		}
		System.out.println("your journey date is :" + TravelDate);
		DayOfWeek WeekValue = TravelDate.getDayOfWeek();
		orgBook = Journeydetails.Bookings * 3;
		BusServiceDB.GetBusBookingDetails(Journeydetails.Source, Journeydetails.Destination);
		BusNumber = BusServiceDB.RetrieveBusNumber(Constants.DoConnect(), Journeydetails.Source, Journeydetails.Destination);
		System.out.println("Total available buses in this route : "+BusServiceDB.availableBuses);
		if(BusServiceDB.availableBuses>=2) {
			System.out.print("Enter the bus Number : ");
			BusNumber=Constants.sc.nextInt();
		}
		if (Journeydetails.Bookings <= 5) {
			System.out.println("getting availability");
			//taking the bus number from user
			//and maintain the entries value in get bus booking details
			int AvailableSeats = BusServiceDB.seats;
			System.out.println("available seats : " + AvailableSeats);
			if ((AvailableSeats <= Journeydetails.Bookings)) {// 5->seats

				System.out.println("not availabile to book the tickets");
				System.out.println("we could not process the booking on this particular ticket number !!!");
				System.out.println("application terminated !!!");
			} else {
				System.out.println("available");
				int ActualAmount = BusServiceDB.amount * Journeydetails.Bookings;
				System.out.println("amount : " + BusServiceDB.amount);
				System.out.println("Ticket price :\t" + ActualAmount);
				TaxAmount = ActualAmount / 18;
				System.out.println("taxes :\t\t " + TaxAmount);
				FareAmount = ActualAmount + TaxAmount;
				RemainingSeats = BusServiceDB.seats - Journeydetails.Bookings;
				if (WeekValue.getValue() > 5) {
					ActualAmount = (ActualAmount + 200) * Journeydetails.Bookings;
					System.out.println("Total price :\t" + FareAmount);
					System.out.println("remaining seats :" + RemainingSeats);
//					int BusNumberReceived = BusServiceDB.RetrieveBusNumber(Constants.DoConnect(), Journeydetails.Source, Journeydetails.Destination);
////					System.out.println("Total available buses in this route : "+BusServiceDB.availableBuses);
////					if(BusServiceDB.availableBuses>=2) {
////						System.out.print("Enter the bus Number : ");
////						BusNumberReceived=Constants.sc.nextInt();
////					}
					System.out.println("Bus Number : "+BusNumber);
					BusServiceDB.UpdateSeats(Constants.DoConnect(), RemainingSeats, BusNumber);

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
//		int retrieveBusNumber = BusServiceDB.RetrieveBusNumber(Constants.DoConnect(), Journeydetails.Source,
//				Journeydetails.Destination);
		BusServiceDB.InsertDetails(Constants.DoConnect(), Journeydetails.Bookings, Journeydetails.Source,
				Journeydetails.Destination, TaxAmount, FareAmount, BusNumber);
//		int BusNumberReceived = BusServiceDB.RetrieveBusNumber(Constants.DoConnect(), Journeydetails.Source, Journeydetails.Destination);
		BusServiceDB.UpdateSeats(Constants.DoConnect(), RemainingSeats, BusNumber);

		System.out.println("booking value : " + Journeydetails.Bookings);
		BusServiceDB.availableBuses=0;
		System.out.println("done the process");
		Booker();
	}

	public static void BookingPassengerInformation() throws SQLException {
		System.out.println("Enter the name : ");
		name = Constants.sc.next();
		System.out.println(name);
		System.out.println("Enter the Mobile number : ");
		number = Constants.sc.next();
		System.out.println(number);
		System.out.println("Enter the age : ");
		age = Constants.sc.nextInt();
		System.out.println(age);
	}

	public static void Booker() throws SQLException {
		switch (Journeydetails.Bookings) {
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