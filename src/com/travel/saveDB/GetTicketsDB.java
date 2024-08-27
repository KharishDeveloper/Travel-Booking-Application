package com.travel.saveDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.travel.logic.UserJourneyRegister;

public class GetTicketsDB {

	public static void GetInformationFromDetails(Connection con, String id) throws SQLException {
		String sql = "select HTTS,mobile,fare,bookings,travelDate,startingplace,destination,ticketStatus from details where HTTS=?;";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, id);
		ResultSet result = pst.executeQuery();
		ResultSetMetaData data = result.getMetaData();
		int columns = data.getColumnCount();
		while (result.next()) {
			for (int i = 1; i <= columns; i++) {
				System.out.println(data.getColumnName(i) + " \t\t: " + result.getString(i));
			}
		}
	}

	public static void GetPassengerInformation(Connection con, String id) throws SQLException{
		String Query = "select booking1_name,booking1_number,booking1_age,booking2_name, booking2_number,booking2_age,booking3_name, booking3_number,booking3_age,booking4_name, booking4_number,booking4_age,booking5_name, booking5_number,booking5_age from bookings where HTTS_ID=?;";
		PreparedStatement stmt = con.prepareStatement(Query);
		stmt.setString(1, id);
		ResultSet set = stmt.executeQuery();
		ResultSetMetaData data = set.getMetaData();
		while(set.next()) {
			System.out.println(UserJourneyRegister.orgBook);
			for(int i=1;i<=UserJourneyRegister.orgBook;i=i+3) {
				System.out.println(data.getColumnName(i)+" : "+set.getString(i));
				System.out.println(data.getColumnName(i+1)+" : "+set.getString(i+1));
				System.out.println(data.getColumnName(i+2)+" : "+set.getString(i+2));
			}
		}
	}
}
