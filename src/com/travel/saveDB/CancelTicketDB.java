package com.travel.saveDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.entity.Journeydetails;
import com.travel.entity.UserServiceLogin;
import com.travel.logic.UserServiceTravelTickets;
import com.travel.properties.MainMenu;

public class CancelTicketDB {

	public static void GettingCancelTicketInformation(Connection connect, String id) throws SQLException {

		String sqlSelect = "select travelDate,startingplace,destination,bookings from details where HTTS=?;";
		PreparedStatement statement = connect.prepareStatement(sqlSelect);
		statement.setString(1, id);
		ResultSet executeQuery = statement.executeQuery();
		if (executeQuery.next()) {
			Journeydetails.Date = executeQuery.getString(1);
			Journeydetails.Source = executeQuery.getString(2);
			Journeydetails.Destination = executeQuery.getString(3);
			Journeydetails.Bookings = executeQuery.getInt(4);
			String sql = "insert into cancellation values(?,?,?,?,?,?,CURRENT_TIMESTAMP());";// entering the values in
			// cancellation table

			System.out.println("travel date :" + Journeydetails.Date);
			PreparedStatement InsertCancel = connect.prepareStatement(sql);
			InsertCancel.setString(1, id);
			InsertCancel.setString(2, UserServiceLogin.MobileNumber);
			InsertCancel.setString(3, Journeydetails.Date);
			InsertCancel.setString(4, Journeydetails.Source);
			InsertCancel.setString(5, Journeydetails.Destination);
			InsertCancel.setInt(6, Journeydetails.Bookings);
			boolean b = InsertCancel.execute();
			String sql1 = "delete from bookings where HTTS_ID=?;";
			PreparedStatement deletebook = connect.prepareStatement(sql1);
			deletebook.setString(1, id);
			System.out.println("deleting bookings info");
			boolean execute = deletebook.execute();
			System.out.println("row(s) deleted in details table : " + b);
			String sql2 = "delete from details where HTTS=?;";
			PreparedStatement stmt2 = connect.prepareStatement(sql2);
			stmt2.setString(1, id);
			boolean c = stmt2.execute();
			System.out.println("Successfully deleted the "+id +" current booking information as per your request !!!");
			MainMenu.Main();
		} else {
			System.out.println("not available to cancel the upcoming travel !!!");
			MainMenu.Main();
		}
	}
}
