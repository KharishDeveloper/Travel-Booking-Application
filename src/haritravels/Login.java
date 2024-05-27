package haritravels;

import java.io.IOException;
import java.sql.SQLException;

public class Login {

	public static void login_details() throws IOException, NullPointerException, SQLException {
		System.out.println(
				"1.plan the journey \t 2.get the tickets\n 3. reschedule \t 4. change the password \n 5. check the travelstatus \n 6. logout");
		int Choice = Constants.sc.nextInt();
		switch (Choice) {
		case 1:
			Booking.date();
			break;
		case 2:

			Tickets.Display_Tickets();
			break;
		case 3:
			Edit.reschedule();
			break;
		case 4:
			Change.changing();
			break;
		case 5:
			Travelstatus.checktravelstatus();
			break;

		case 6:
			Logout.logout();
			break;
		default:
			System.out.println("select the certain one ");
			login_details();
			Choice = Constants.sc.nextInt();
			break;
		}

	}
}
