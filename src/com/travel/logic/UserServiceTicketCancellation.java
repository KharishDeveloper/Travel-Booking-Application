package com.travel.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.entity.Journeydetails;
import com.travel.entity.UserServiceLogin;
import com.travel.properties.Constants;
import com.travel.properties.MainMenu;
import com.travel.saveDB.CancelTicketDB;

public class UserServiceTicketCancellation {

	public static void CancelTicket() throws SQLException {
		System.out.println("Enter the id : ");
		UserServiceTravelTickets.id = Constants.sc.next();// take the HTTS id for retrieving

//		System.out.println("Here is your upcoming travel details !!!");
//		UserServiceTravelTickets.GetTicketInformation();
//		System.out.println("if you don't want to cancel then click 1 otherwise click 2");
//		int cancelchoicer = Constants.sc.nextInt();
//		switch (cancelchoicer) {
//
//		case 1:
//			MainMenu.Main();
//			break;
//		case 2:

		Connection connect = Constants.DoConnect();

//		String sqlSelect = "select travelDate,startingplace,destination,bookings from details where HTTS=?;";
//		PreparedStatement statement = connect.prepareStatement(sqlSelect);
//		statement.setString(1, UserServiceTravelTickets.id);
//		ResultSet executeQuery = statement.executeQuery();
//		if(executeQuery.next()) {
//			Journeydetails.Date=executeQuery.getString(1);
//			Journeydetails.Source=executeQuery.getString(2);
//			Journeydetails.Destination=executeQuery.getString(3);
//			Journeydetails.Bookings=executeQuery.getInt(4);
//		}
		CancelTicketDB.GettingCancelTicketInformation(connect, UserServiceTravelTickets.id);
//		String sql = "insert into cancellation values(?,?,?,?,?,?,CURRENT_TIMESTAMP());";// entering the values in
//																							// cancellation
//		// table
//
//		System.out.println("travel date :" + Journeydetails.Date);
//		PreparedStatement InsertCancel = connect.prepareStatement(sql);
//		InsertCancel.setString(1, UserServiceTravelTickets.id);
//		InsertCancel.setString(2, UserServiceLogin.MobileNumber);
//		InsertCancel.setString(3, Journeydetails.Date);
//		InsertCancel.setString(4, Journeydetails.Source);
//		InsertCancel.setString(5, Journeydetails.Destination);
//		InsertCancel.setInt(6, Journeydetails.Bookings);
//		boolean b = InsertCancel.execute();
////		System.out.println("row inserted : " + b);
//		String sql1 = "delete from bookings where HTTS_ID=?;";
//		PreparedStatement deletebook = connect.prepareStatement(sql1);
//		deletebook.setString(1, UserServiceTravelTickets.id);
//		System.out.println("deleting bookings info");
//		boolean execute = deletebook.execute();
//		System.out.println("row(s) deleted in details table : " + b);
//		String sql2 = "delete from details where HTTS=?;";
//		PreparedStatement stmt2 = connect.prepareStatement(sql2);
//		stmt2.setString(1, UserServiceTravelTickets.id);
//		boolean c = stmt2.execute();
//		System.out.println("row(s) deleted in bookings table : " + c);
//		}
//		String sql3="update from buses set Tatkal_Confirmations where busNumber=?;";
		/*
		 * write a sql query for deleting the data in both details and bookings table
		 * 
		 * In sql , create a table that with maintain cancellation records DONE
		 * 
		 * and add the data in cancellation table.
		 * 
		 * and also update the bus seat numbers
		 * 
		 */
//		System.out.println("Successfully deleted the "+UserServiceTravelTickets.id +" current booking information as per your request !!!");
//		MainMenu.Main();
	}
}
