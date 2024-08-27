package com.travel.saveDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.logic.UserFeatureTravelStatus;

public class TravelStatusFeatureDB {

	public static String GetTravelDate(Connection con, String HTTSID) throws SQLException {
		String sql = "select traveldate from details where HTTS=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, HTTSID);
		ResultSet set = stmt.executeQuery();
		if (set.next()) {
			return set.getString(1);
		}
		return null;

	}

	public static void UpdateTravelStatus(Connection con, String Date, String HTTS) throws SQLException {
		String travelStatus = UserFeatureTravelStatus.TravelStatus(Date);
		System.out.println(travelStatus);
		String sql = "update bookings set TravelStatus=? where HTTS_ID=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, travelStatus);
		stmt.setString(2, HTTS);
		stmt.executeUpdate();
	}
}
