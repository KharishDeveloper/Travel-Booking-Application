package haritravels;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Tickets extends Travelstatus {
	public static String Display_Tickets() throws SQLException, NullPointerException, IOException {
		String s = Travelstatus.TravelStatus;
		System.out.println(s + "s is travelstatus");
		String HTTSID;
		System.out.println("enter the HTTS_ID to get the ticket details");
		HTTSID = Constants.sc.next();
//		System.out.println("HTTS ID : "+TravelStatus.mob1);
		Connection connection = Constants.GetConnection();
		String sql = "select travelDate,startingplace,destination,fare,age,ticketStatus,bookings from details where HTTS=?;";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, HTTSID);
		ResultSet result = pst.executeQuery();
		ResultSetMetaData rsmd = result.getMetaData();
		int j = rsmd.getColumnCount();
//		TravelStatus.checktravelstatus();
//		System.out.println(j +"count");
//		System.out.println("here");
//		System.out.println(set.getString(1));
		if (result.next()) {
			if (Travelstatus.TravelStatus == "completed the travel") {
				System.out.println("no bookings");
				Login.login_details();
			} else {
				System.out.println("here it is ");
				System.out.println("\nyour travel date :" + result.getString(1) + "\nsource :" + result.getString(2)
						+ "\n Destination :" + result.getString(3));
				System.out.println("Total bill :" + result.getString(4) + "\nage :" + result.getString(5)
						+ "\nticket-status :" + result.getString(6) + "\ntotal bookings :" + result.getString(7));// +set.getString(8)+//"\nbooking
				// name
				// :"+set.getString(9));
//			System.out.println("bookings number :"+set.getString(10));
//			System.out.println(set.getInt(8)+"this is 8");
//			}

				String Query = "select booking1_name,booking1_number,booking1_age,booking2_name, booking2_number,booking2_age,booking3_name, booking3_number,booking3_age,booking4_name, booking4_number,booking4_age,booking5_name, booking5_number,booking5_age from bookings where HTTS_ID=?;";
				PreparedStatement ps = Constants.GetConnection().prepareStatement(Query);
				ps.setString(1, HTTSID);
				ResultSet result1 = ps.executeQuery();
				if (result1.next()) {
//			System.out.println(set.columncount);
//			for(int i=1;i<=set.getInt(8);i++) {
//			int i = set.getInt(9);
					// travel status implement first.
					System.out.println("booking name :" + result1.getString(1));
					System.out.println("bookings number :" + result1.getString(2));
					System.out.println("booking age :" + result1.getString(3));
					System.out.println("booking name :" + result1.getString(4));
					System.out.println("bookings number :" + result1.getString(5));
					System.out.println("booking age :" + result1.getString(6));
					System.out.println("booking name :" + result1.getString(7));
					System.out.println("bookings number :" + result1.getString(8));
					System.out.println("booking age :" + result1.getString(9));
					System.out.println("booking name :" + result1.getString(10));
					System.out.println("bookings number :" + result1.getString(11));
					System.out.println("booking age :" + result1.getString(12));
					System.out.println("booking name :" + result1.getString(13));
					System.out.println("bookings number :" + result1.getString(14));
					System.out.println("booking age :" + result1.getString(15));
					
//			}
//			else {

					System.out.println("enter option : ");
					System.out.println("1. Reschedule ticket \t 2. Main Screen");
					int ScreenValueDirection = Constants.sc.nextInt();
					if (ScreenValueDirection == 1) {
						Edit.reschedule();
					} else {
						Login.login_details();
					}

				}
			}
		} else {
			System.out.println("could not found your payment details !!!");
			System.out.println("");
			System.out.println("else");
			System.out.println("");
			System.out.println("our team will check your payments and");
			System.out.println("initialiate the refund or get the tickets");
			try {
				Login.login_details();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return TravelStatus;

	}
//		return "";
}