package com.travel.saveDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.travel.entity.Journeydetails;
import com.travel.entity.UserServiceLogin;
import com.travel.logic.UserJourneyRegister;
import com.travel.properties.Constants;

public class BusServiceDB {

	public static int seats, amount;

	public static void GetBusDetails() throws SQLException {
		Connection connection = Constants.DoConnect();
		String Query11 = "select * from buses;";
		PreparedStatement ps1 = connection.prepareStatement(Query11);
		ResultSetMetaData md = ps1.getMetaData();
		ResultSet Result2 = ps1.executeQuery();
		System.out.println(" ");
		while (Result2.next()) {
			System.out.println(md.getColumnName(1) + " : " + Result2.getString(1) + "\t" + md.getColumnName(2) + " : "
					+ Result2.getString(2));
			System.out.println(md.getColumnName(3) + " : " + Result2.getString(3) + "\t" + md.getColumnName(4) + " : "
					+ Result2.getString(4) + "\t" + md.getColumnName(5) + " : " + Result2.getString(5) + "\t");
			System.out.println(" ");
//			seats = Result2.getInt(5);
//			amount = Result2.getInt(4);
		}
	}

	public static void GetBusBookingDetails(String source, String Destination) throws SQLException {
		Connection connection = Constants.DoConnect();
		String Query1 = "select * from buses where (startplace,destination)=(?,?);";
		PreparedStatement ps1 = connection.prepareStatement(Query1);
		ps1.setString(1, source);
		ps1.setString(2, Destination);
		ResultSet result1 = ps1.executeQuery();
		ResultSetMetaData rs = result1.getMetaData();
		System.out.println("a");
		while (result1.next()) {
//			rs.getColumnName(1);
//			System.out.println("b");
//			System.out.print(rs.getColumnName(1) + " : \t" + result1.getString(1));
//			System.out.print("\t");
//			System.out.print(rs.getColumnName(2) + " : \t" + result1.getString(2));
//			System.out.print("\t");
//			System.out.println(" ");
//			System.out.println("---------------------------------");
//			System.out.print(rs.getColumnName(3) + " : \t" + result1.getString(3));
//			System.out.print("\t\t");
//			System.out.print(rs.getColumnName(4) + " : \t" + result1.getString(4));
//			System.out.print("\t");
//			System.out.println(" ");
//			System.out.print(rs.getColumnName(5) + " : \t" + result1.getString(5));
//			System.out.print("\t");
//			System.out.println(" ");
			System.out.println("");
			for (int i = 1; i <= 5; i++) {
				System.out.print(rs.getColumnName(i)+" : "+ result1.getString(i) + " \t");
				if (i == 3) {
					System.out.println("");
					System.out.println("-----------------------------------");
				}
			}

			seats = result1.getInt(5);
			amount = result1.getInt(4);
			UserJourneyRegister.BusNumber=result1.getInt(1);
//			return true;
//		} else {
//			System.out.println("There is no bus bookings based on your places !!!");
		}
//		return false;
	}

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
			int FinalAmount, int BusNumber) throws SQLException {
		System.out.println("HTTSId [generated id] is : " + Constants.HTTSID);
		String Query4 = "insert into details(mobile,presentDate, travelDate, startingplace, destination, taxes, fare, ticketStatus, bookings, HTTS, BusNumber) values(?,CURRENT_TIMESTAMP(),?,?,?,?,?,?,?,?,?);";
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
		pst1.setInt(10, BusNumber);// bus number update
		pst1.execute();

		String sql = "insert into bookings(HTTS_ID, Bookings,mobile) values(?,?,?);";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, Constants.HTTSID);
		stmt.setInt(2, Journeydetails.Bookings);
		stmt.setString(3, UserServiceLogin.MobileNumber);
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

	public static void UpdateSeats(Connection con, int RSeats, String s, String d) throws SQLException {
		String Query2 = "update buses set seats=? where (startplace,destination)=(?,?);";
		PreparedStatement ps2 = con.prepareStatement(Query2);
		ps2.setInt(1, RSeats);
		ps2.setString(2, s);
		ps2.setString(3, d);
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
		return -1;
	}

	public static void check(Connection con) throws SQLException {
		String sql = "select * from buses where (startplace, destination)=(?,?);";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, "Tenali");
		stmt.setString(2, "Guntur");
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			System.out.println(set.getString(1));
		}

	}
}