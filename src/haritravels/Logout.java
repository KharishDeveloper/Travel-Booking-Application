package haritravels;

import java.io.IOException;
import java.sql.SQLException;

public class Logout {

	public static void logout() throws IOException, NullPointerException, SQLException {
		boolean isLoggedIn = true;
		if (isLoggedIn == true) {
			isLoggedIn = false;
			System.out.println("logout successful");
			Display.StartingScreen();
		} else {
			System.out.println("error !!!");
		}
	}
}
