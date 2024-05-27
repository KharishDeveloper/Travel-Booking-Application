package haritravels;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Travelstatus {
	static String MobileNumber = "";
	static String TravelStatus = "";

	public static String checktravelstatus() throws SQLException, NullPointerException, IOException {
		System.out.println("enter HTTS !!!");
		MobileNumber = Constants.sc.next();
		String Query1 = "select mobile from details where HTTS=?;";
		PreparedStatement ps = Constants.GetConnection().prepareStatement(Query1);
		ps.setString(1, MobileNumber);
		ResultSet result1 = ps.executeQuery();
		if (result1.next()) {
			try {
				String url = "select travelDate, presentDate from details where HTTS=?;";
				PreparedStatement ps1 = Constants.GetConnection().prepareStatement(url);
				ps1.setString(1, MobileNumber);
				ResultSet result2 = ps1.executeQuery();
				if (result2.next()) {
					String StoredTravelDate = result2.getString(1);
//			
//			String b = set.getString(2);
//			try {

					LocalDate TravelDate = LocalDate.parse(StoredTravelDate);

//			LocalDate B = LocalDate.parse(b);
					LocalDate Presentdate = LocalDate.now();// today's date
					System.out.println(TravelDate);// travel date

					long Days = ChronoUnit.DAYS.between(Presentdate, TravelDate);
					System.out.println(Days);

					if (Days >= 2) {
						TravelStatus = "Travel within " + Days + " days";
//				System.out.println("your journey within "+ value +" days");
//				System.out.println(travelstatus);

					} else if (Days == 0) {
						TravelStatus = "Today is your travel, ready to travel / travelling";
//				System.out.println("travelstatus : travelling");
//				System.out.println(travelstatus);
					} else if (Days == (-1)) {
						TravelStatus = "travelling / completed";
//				System.out.println(travelstatus);
					} else if (Days == (-2)) {
						TravelStatus = "completed the travel";
//				System.out.println(travelstatus);
					} else if (Days <= (-3)) {
						TravelStatus = "completed the travel";
//				System.out.println(travelstatus);
					} else if (Days == 1) {
						TravelStatus = "tomorrow is your travel date";
//				System.out.println(travelstatus);
					}

//			else {
					System.out.println(TravelStatus);
					String sql = "update bookings set travelstatus=? where HTTS_ID=?;";
//				sql = "insert into bookings(mobile, travelStatus) values(?,?);";
					PreparedStatement ps2 = Constants.GetConnection().prepareStatement(sql);
					ps2.setString(1, TravelStatus);
					ps2.setString(2, MobileNumber);
					int update = ps2.executeUpdate();
					if (update == 1) {
						System.out.println("updated successfully !!!");
						Login.login_details();
						return TravelStatus;
//					Tickets.Display_Tickets();
					} else {
						System.out.println("unable to fetch the details !!!");
						Login.login_details();
					}
//			}
//			catch(Exception i) {
//				System.out.println("throw a exception on loaclDte");
//			}
				}
//				System.out.println("rem !!!");
//			System.out.println("getting data");
//		}

			} catch (NullPointerException e) {
				System.out.println("first you can book the travel ticket");
				System.out.println("after it can display the travelstatus !!!");
				Booking.date();
			}
		} else {
			System.out.println("mobile number not exist");
			System.out.println("you can try with another mobile number or create the account !!");
			System.out.println("1. retry the travelstatus process with another mobile number \n 2. create the account");
			int choice = Constants.sc.nextInt();
			switch (choice) {
			case 1:
//				TravelStatus.che
				Travelstatus.checktravelstatus();
				break;

			case 2:
				Menu.menu();
				break;
			default:
				System.out.println("enter certain one !!");
				Travelstatus.checktravelstatus();
				break;
			}
		}
		return TravelStatus;
	}
}
