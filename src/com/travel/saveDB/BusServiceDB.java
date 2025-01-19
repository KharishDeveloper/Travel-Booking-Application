package com.travel.saveDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.travel.entity.Journeydetails;
import com.travel.entity.UserServiceLogin;
import com.travel.logic.UserJourneyRegister;
import com.travel.logic.UserServiceRetrievedDataCheckForSplit;
import com.travel.properties.Constants;

public class BusServiceDB {

	public static int seats, amount, availableBuses;
	public static String HaltStations = "",ReservationClass="";

	public static void GetBusDetails(String Source, String Destination) throws SQLException {
		Connection connection = Constants.DoConnect();
		String Query11 = "select * from buses;";
		PreparedStatement ps1 = connection.prepareStatement(Query11);
//		ResultSetMetaData md = ps1.getMetaData();
		ResultSet set = ps1.executeQuery();
		System.out.println(" ");
		while (set.next()) {
			HaltStations = set.getString(19);
//			System.out.println("retrieved from GetBusDetails"+HaltStations);
			if (set.getString(3).contains(Source) && set.getString(4).contains(Destination)) {
				System.out.println("you entered into train at source place and left out at the destination");
				System.out.println("your bus number / name is : "+set.getString(1)+" / "+set.getString(2));
				UserJourneyRegister.BusNumber=set.getString(1);
				UserJourneyRegister.BusName=set.getString(2);
				availableBuses++;
				System.out.println("-----------------");
			} else if (set.getString(3).contains(Source) && set.getString(19).contains(Destination)) {
				System.out.println("you entered into train at source place and left out at the middle");
				System.out.println("your bus number / name is : "+set.getString(1)+" / "+set.getString(2));
				UserJourneyRegister.BusNumber=set.getString(1);
				UserJourneyRegister.BusName=set.getString(2);
				availableBuses++;
				System.out.println("-----------------");
			} else if (set.getString(19).contains(Source) && set.getString(19).contains(Destination)) {
				boolean main = UserServiceRetrievedDataCheckForSplit.main(Source, Destination);
				if (main) {
					System.out.println("you entered at middle and left out at the middle also !!!");
					System.out.println("your bus number / name is : "+set.getString(1)+" / "+set.getString(2));
					UserJourneyRegister.BusNumber=set.getString(1);
					UserJourneyRegister.BusName=set.getString(2);
					availableBuses++;
					System.out.println("-----------------");
				}
			} else if (set.getString(19).contains(Source) && set.getString(4).contains(Destination)) {
				System.out.println("you entered into train at middle and left out at the end !!!");
				System.out.println("your bus number / name is : "+set.getString(1)+" / "+set.getString(2));
				UserJourneyRegister.BusNumber=set.getString(1);
				UserJourneyRegister.BusName=set.getString(2);
				availableBuses++;
				System.out.println("-----------------");
			}

			seats = set.getInt(5);
//			UserJourneyRegister.BusNumber=set.getString(1);
		}
	}

//	public static void GetBusBookingDetails(String source, String Destination) throws SQLException {
//		Connection connection = Constants.DoConnect();
//		String Query1 = "select * from buses where (startplace,destination)=(?,?);";
//		PreparedStatement ps1 = connection.prepareStatement(Query1);
//		ps1.setString(1, source);
//		ps1.setString(2, Destination);
//		ResultSet result1 = ps1.executeQuery();
//		ResultSetMetaData rs = result1.getMetaData();
//		System.out.println("a");
//		while (result1.next()) {
//
//			availableBuses++;
////			rs.getColumnName(1);
////			System.out.println("b");
////			System.out.print(rs.getColumnName(1) + " : \t" + result1.getString(1));
////			System.out.print("\t");
////			System.out.print(rs.getColumnName(2) + " : \t" + result1.getString(2));
////			System.out.print("\t");
////			System.out.println(" ");
////			System.out.println("---------------------------------");
////			System.out.print(rs.getColumnName(3) + " : \t" + result1.getString(3));
////			System.out.print("\t\t");
////			System.out.print(rs.getColumnName(4) + " : \t" + result1.getString(4));
////			System.out.print("\t");
////			System.out.println(" ");
////			System.out.print(rs.getColumnName(5) + " : \t" + result1.getString(5));
////			System.out.print("\t");
////			System.out.println(" ");
//			System.out.println("");
//			for (int i = 1; i <= 5; i++) {
//				System.out.print(rs.getColumnName(i) + " : " + result1.getString(i) + " \t");
//				if (i == 3) {
//					System.out.println("");
//					System.out.println("-----------------------------------");
//				}
//			}
//			System.out.println("");
//
//			seats = result1.getInt(5);
////			amount = result1.getInt(4);
////			UserJourneyRegister.BusNumber=result1.getInt(1);
////			return true;
////		} else {
////			System.out.println("There is no bus bookings based on your places !!!");
//		}
////		return false;
//	}

//	public static void GetBusBookingDetails(String source, String Destination) throws SQLException {
//		String sql = "select * from buses where (startplace, destination)=(?,?);";
//		Connection con = Constants.DoConnect();
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setString(1, source);
//		stmt.setString(2, Destination);
//		ResultSet set = stmt.executeQuery();
//		while (set.next()) {
//			System.out.println(set.getString(1));
//			for (int i = 1; i <= 5; i++) {
//				System.out.print(set.getString(i) + " \t");
//				if (i == 3) {
////					System.out.println("/n");
////					System.out.println("-----------------------------------");
//				}
//				System.out.println();
//			}
////			return "0";//true
//		}
////		return "-1";//false
//	}

	public static void InsertDetails(Connection con, int Bookings, String source, String destination, float tax,
			int FinalAmount, String BusNumber) throws SQLException {
		System.out.println("HTTSId [generated id] is : " + Constants.HTTSID);
		String Query4 = "insert into details(mobile,presentDate, travelDate, startingplace, destination, taxes, fare, ticketStatus, bookings, HTTS) values(?,CURRENT_TIMESTAMP(),?,?,?,?,?,?,?,?);";
		PreparedStatement pst1 = con.prepareStatement(Query4);
		pst1.setString(1, UserServiceLogin.MobileNumber);
		pst1.setString(2, Journeydetails.Date);
		pst1.setString(3, source);
		pst1.setString(4, destination);
		pst1.setFloat(5, tax);
		pst1.setInt(6, FinalAmount);
		pst1.setString(7, "success");
		pst1.setInt(8, Bookings);
		pst1.setString(9, Constants.HTTSID);
//		pst1.setString(10, BusNumber);// bus number update
		pst1.execute();

		String sql = "insert into bookings(HTTS_ID, Bookings,mobile,BusNumber,BusName) values(?,?,?,?,?);";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, Constants.HTTSID);
		stmt.setInt(2, Journeydetails.Bookings);
		stmt.setString(3, UserServiceLogin.MobileNumber);
		stmt.setString(4, UserJourneyRegister.BusNumber);
		stmt.setString(5, UserJourneyRegister.BusName);
		stmt.execute();

	}

	public static void InsertPassenger1(Connection con, String RID, String name, String number, int age)
			throws SQLException {
		String sql = "update bookings set booking1_name=?,booking1_number=?,booking1_age=? where HTTS_ID=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, number);
		stmt.setInt(3, age);
		stmt.setString(4, RID);
		stmt.executeUpdate();
		Journeydetails.Bookings--;
		System.out.println("done the booking " + Journeydetails.Bookings);

	}

	public static void InsertPassenger2(Connection con, String RID, String name, String number, int age)
			throws SQLException {
		String sql = "update bookings set booking2_name=?,booking2_number=?,booking2_age=? where HTTS_ID=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, number);
		stmt.setInt(3, age);
		stmt.setString(4, RID);
		stmt.executeUpdate();
		Journeydetails.Bookings--;
		System.out.println("done the booking " + Journeydetails.Bookings);

	}

	public static void InsertPassenger3(Connection con, String RID, String name, String number, int age)
			throws SQLException {
		String sql = "update bookings set booking3_name=?,booking3_number=?,booking3_age=? where HTTS_ID=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, number);
		stmt.setInt(3, age);
		stmt.setString(4, RID);
		stmt.executeUpdate();
		Journeydetails.Bookings--;
		System.out.println("done the booking " + Journeydetails.Bookings);

	}

	public static void InsertPassenger4(Connection con, String RID, String name, String number, int age)
			throws SQLException {
		String sql = "update bookings set booking4_name=?,booking4_number=?,booking4_age=? where HTTS_ID=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, number);
		stmt.setInt(3, age);
		stmt.setString(4, RID);
		stmt.executeUpdate();
		Journeydetails.Bookings--;
		System.out.println("done the booking " + Journeydetails.Bookings);

	}

	public static void InsertPassenger5(Connection con, String RID, String name, String number, int age)
			throws SQLException {
		String sql = "update bookings set booking5_name=?,booking5_number=?,booking5_age=? where HTTS_ID=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, name);
		stmt.setString(2, number);
		stmt.setInt(3, age);
		stmt.setString(4, RID);
		stmt.executeUpdate();
		Journeydetails.Bookings--;
		System.out.println("done the booking " + Journeydetails.Bookings);

	}

	public static void UpdateSeats(Connection con, int RSeats, String BusNumber2) throws SQLException {
		String Query2 = "update buses set seats=? where BusNumber=?";
		PreparedStatement ps2 = con.prepareStatement(Query2);
		ps2.setInt(1, RSeats);
		ps2.setString(2, BusNumber2);
		ps2.executeUpdate();
	}

	public static int RetrieveBusNumber(Connection con, String Source, String Destination) throws SQLException {
		String sql = "select BusNumber from buses where (startplace,destination)=(?,?);";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, Source);
		statement.setString(2, Destination);
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			return set.getInt(1);
		}
		return 0;
	}

	public static void SelectReservationClass(Connection con, String BusNumber) throws SQLException {
		String sql = "select * from buses where busNumber=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, BusNumber);
		ResultSetMetaData rsmd = stmt.getMetaData();
		ResultSet set = stmt.executeQuery();
//		int i=0;
		System.out.println("-----------------------------------------------------------");
		while (set.next()) {
			System.out.println(set.getString(1) + " / " + set.getString(2));
			for (int i = 9; i <= rsmd.getColumnCount() - 2; i++) {
				System.out.println(rsmd.getColumnName(i) + " : " + set.getString(i));

			}
			System.out.println("-----------------------------------------------------------");
			System.out.println("Enter the class-name as it is");
			ReservationClass=Constants.sc.next();
			amount=set.getInt(ReservationClass);
//			System.out.println("amount : "+amount);
			
		}

	}

}