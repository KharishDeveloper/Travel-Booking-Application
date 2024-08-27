package com.travel.saveDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
		if (result1.next()) {
			rs.getColumnName(1);
			System.out.print(rs.getColumnName(1) + " : \t" + result1.getString(1));
			System.out.print("\t");
			System.out.print(rs.getColumnName(2) + " : \t" + result1.getString(2));
			System.out.print("\t");
			System.out.println(" ");
			System.out.println("---------------------------------");
			System.out.print(rs.getColumnName(3) + " : \t" + result1.getString(3));
			System.out.print("\t\t");
			System.out.print(rs.getColumnName(4) + " : \t" + result1.getString(4));
			System.out.print("\t");
			System.out.println(" ");
			System.out.print(rs.getColumnName(5) + " : \t" + result1.getString(5));
			System.out.print("\t");
			System.out.println(" ");
			seats = result1.getInt(5);
			amount = result1.getInt(4);
		}
		else {
			System.out.println("There is no bus bookings based on your places !!!");
		}
	}

	public static void InsertDetails(Connection con, int Bookings, String source, String destination, float tax,
			int FinalAmount) throws SQLException {
		System.out.println("HTTSId [generated id] is : " + Constants.HTTSID);
		String Query4 = "insert into details(mobile,presentDate, travelDate, startingplace, destination, taxes, fare, ticketStatus, bookings, HTTS) values(?,curdate(),?,?,?,?,?,?,?,?);";
		PreparedStatement pst1 = con.prepareStatement(Query4);
		pst1.setString(1, UserServiceLogin.MobileNumber);
		pst1.setString(2, UserJourneyRegister.Date);
		pst1.setString(3, source);
		pst1.setString(4, destination);
		pst1.setFloat(5, tax);
		pst1.setInt(6, FinalAmount);
		pst1.setString(7, "success");
		pst1.setInt(8, Bookings);
		pst1.setString(9, Constants.HTTSID);
		pst1.execute();

		String sql = "insert into bookings(HTTS_ID, Bookings,mobile) values(?,?,?);";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, Constants.HTTSID);
		stmt.setInt(2, UserJourneyRegister.Bookings);
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
		UserJourneyRegister.Bookings--;
		System.out.println("done the booking " + UserJourneyRegister.Bookings);

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
		UserJourneyRegister.Bookings--;
		System.out.println("done the booking " + UserJourneyRegister.Bookings);

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
		UserJourneyRegister.Bookings--;
		System.out.println("done the booking " + UserJourneyRegister.Bookings);

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
		UserJourneyRegister.Bookings--;
		System.out.println("done the booking " + UserJourneyRegister.Bookings);

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
		UserJourneyRegister.Bookings--;
		System.out.println("done the booking " + UserJourneyRegister.Bookings);

	}

	public static void UpdateSeats(Connection con, int RSeats, String s, String d) throws SQLException {
		String Query2 = "update buses set seats=? where (startplace,destination)=(?,?);";
		PreparedStatement ps2 = con.prepareStatement(Query2);
		ps2.setInt(1, RSeats);
		ps2.setString(2, s);
		ps2.setString(3, d);
		ps2.executeUpdate();
	}
}