package haritravels;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Edit {

	public static PreparedStatement reschedule() throws IOException, NullPointerException, SQLException {
//		Scanner res = new Scanner(System.in);
		System.out.println("enter the HTTS number :");
		String mobile = Constants.sc.next();
		Connection connection = Constants.GetConnection();
//		String sql1 = "select travelDate from users where mobile=?";
		String sql = "SELECT travelDate IS NULL FROM details WHERE HTTS=?;";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, mobile);
		ResultSet result = ps.executeQuery();
//		if(q.next()==false) {
//			System.out.println("first you can create the account !!!");
//			Menu.menu(Menu.registered_users());
//		}

		if (result.next()) {
//			int TravelDateExister = result.getInt(1);
////			System.out.println(TravelDateExister);// 0 ->true
//			// 1-> false
			if (result.getInt(1) == 0) {
				System.out.println("you want to reschedule the ticket");
				System.out.println("1. rescheule the ticket\n 2.Return to main menu");
				int Choice = Constants.sc.nextInt();
				switch (Choice) {
				case 1:
					if (Choice == 1) {
						System.out.println("enter rescheduling details ");
						System.out.println("");
						System.out.println(
								"You can only reschedule the ticket after 10 days from the data which date you choosed previously !!");
						System.out.println("Enter the date :[YYYY-MM-DD]");
						String RescheduleDate = Constants.sc.next();
						String query = "select travelDate from details where HTTS=?;";
						PreparedStatement ps1 = connection.prepareStatement(query);
						ps1.setString(1, mobile);
						ResultSet result1 = ps1.executeQuery();
						if (result1.next()) {
							LocalDate TravelDate = LocalDate.parse(RescheduleDate);
							LocalDate PresentDate = LocalDate.now();
							long Days = ChronoUnit.DAYS.between(PresentDate, TravelDate);
//						System.out.println(value);
							while (Days <= 10) {
								System.out.println("It seems that you entered expired date that means past date");
								System.out.println("You entered a date that is less than 10 days away.");
								RescheduleDate = Constants.sc.next();
								TravelDate = LocalDate.parse(RescheduleDate);
								PresentDate = LocalDate.now();
								Days = ChronoUnit.DAYS.between(PresentDate, TravelDate);

							}
							System.out.println("your updated travel date :" + RescheduleDate);
							Connection Connection = Constants.GetConnection();
							String sql2 = "update details set travelDate=?,rescheduled=? where HTTS=?;";
							PreparedStatement psi = Connection.prepareStatement(sql2);
							psi.setString(1, RescheduleDate);
							psi.setString(2, "yes");
							psi.setString(3, mobile);
//							System.out.println("hello");
							int RescheduleUpdate = psi.executeUpdate();
//							System.out.println("impacted :" + RescheduleUpdate);
							if (RescheduleUpdate == 1) {
								System.out.println("you have successfully completed the reschedulation ");
							}
						}
					} else {
						System.out.println("enter the valid mob nu!!!");
						System.out.println("enter the valid value !!!");
					}
//				break;
				case 2:
					Login.login_details();
					break;
				default:
					System.out.println("please enter the valid one !!!");
				}
			} else {
				System.out.println(
						"it seems like you have not complete your booking \n first complete ticket booking !!!");
				Booking.date();
			}
		} else {
			System.out.println("create the account !!!");
		}
		return ps;
	}
}
