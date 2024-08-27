package com.travel.saveDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RescheduleServiceDB {

	public static void UpdateRescheduleInformation(Connection con,String date,String id) throws SQLException {
		String sql="update details set travelDate=?, rescheduled=? where HTTS=?;";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, date);
		stmt.setString(2, "YES");
		stmt.setString(3, id);
		stmt.executeUpdate();
	}
	
}
