package haritravels;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Menu {

	public static PreparedStatement menu()
			throws NullPointerException, IOException, SQLException, SQLNonTransientConnectionException {
		System.out.println("");
		System.out.println("New user registration ");
		System.out.println(" ");
		System.out.println("Enter the first name :");
		String FirstName = Constants.sc.next();
		System.out.println("Enter the last name :");
		String LastName = Constants.sc.next();
		String FullName = (FirstName + " " + LastName);
		System.out.println("Your name is " + FullName);
		System.out.println("Select your gender :");
		System.out.println("1.Male\n 2.Female \n 3. Others");
		try {
			int Gender = Constants.sc.nextInt();
			switch (Gender) {
			case 1:
				System.out.println("Male");
				break;
			case 2:
				System.out.println("Female");
				break;
			case 3:
				System.out.println("Others");
				break;
			default:
				do {
					System.out.println("Select the valid gender type !");
					System.out.println("Enter again");
					Gender = Constants.sc.nextInt();
				} while (Gender > 4);
			}
		} catch (InputMismatchException e) {
			System.out.println("Enter the numeric value only");
			System.out.println("");
			Menu.menu();
//			System.out.println("you have to enter just reload the application ");
//			Exit.repeat();
		} catch (NoSuchElementException i) {
			System.out.println("Enter the numeric value 1,2,3 only !!!");
//			System.out.println("Raised a exception called NoSuchElemenEexception");
			Menu.menu();

		}

//		System.out.println("accepted mail domains : @ g mail .com only");
		System.out.println("Enter your gmail-id :");
		String Mail = Constants.sc.next();
		while (!((Mail.contains("gmail.com")))) {

//		while((mail_variable.contains("@"))& ((mail_variable.contains(".com")) | (mail_variable.contains(".in"))) & ((mail_variable.contains("gmail")) | (mail_variable.contains("yahoo")))) {
			System.out.println("Enter the valid mail address");
			Mail = Constants.sc.next();
		}
		System.out.println("Enter your mobile number :");
		String MobileNumber = Constants.sc.next();
		while (MobileNumber.length() != 10) {
			System.out.println("enter the valid mobile number !!!");
			MobileNumber = Constants.sc.next();
//			mobile_character_checking = MobileNumber.length();
		}
		String Query = "select mobile from users_data where mobile=?;";
		Connection connection = Constants.GetConnection();
		PreparedStatement ps = connection.prepareStatement(Query);
		ps.setString(1, MobileNumber);
		ResultSet Result = ps.executeQuery();
		if (Result.next()) {
			if (Result.getString(1).equals(MobileNumber)) {
				System.out.println("you are already a registered user");
				System.out.println("just sign-in");
//			Display.starting_screen();

//				System.out.println("you are already a registered user");
				System.out.println(" ");
				System.out.println("You can login to your account !!!");
				System.out.println(" ");
				Display.StartingScreen();
			}

		} else {
			System.out.println("Enter the password :");
			String Password = Constants.sc.next();
			while (Password.length() < 8) {
				// 10 size
				System.out.println("Password length must be 8 characters");
				System.out.println("");
//				System.out.println("set your password strongly");
//				System.out.println("");
				System.out.println("Re-enter your password :");
				Password = Constants.sc.next();
			}
//			System.out.println("password verified");
			System.out.println("");
//		Testdata.registered_users.keySet();
//		System.out.println(d1);//get the key set
//		String url2 = "select mobile from users where mobile=?;";
//		Connection connection = Connection_travels.GetConnection();
//		PreparedStatement ps = connection.prepareStatement(url2);
//		ps.setString(1, register_mobile);
//		ResultSet e = ps.executeQuery();
//		if(e.next()) {
//			if(e.getString(1).equals(register_mobile)) {				
//				System.out.println("you are a existed user");
//			System.out.println("just signin");
////			Display.starting_screen();
//			
//			System.out.println("you are already a registered user");
//			System.out.println(" ");
//			System.out.println("you can login to your account !!!");
//			System.out.println(" ");
//			Display.starting_screen();
////			Sign.sign(Testdata.registered_users);
//			}
//		
//		}
//		else {
			System.out.println("You have successfully completed the registration !!!");
			System.out.println(" ");
			System.out.println("You have login to the account");
			System.out.println(" ");

			String Query1 = "insert into users_data(mobile,email,password,firstName,lastName,fullName) values(?,?,?,?,?,?);";
			PreparedStatement ps1 = Constants.GetConnection().prepareStatement(Query1);
			ps1.setString(1, MobileNumber);
			ps1.setString(2, Mail);
			ps1.setString(3, Password);
			ps1.setString(4, FirstName);
			ps1.setString(5, LastName);
			ps1.setString(6, FullName);
			ps1.executeUpdate();
//		System.out.println(update +"impact");
//		
//		Display.starting_screen();
//Login.login_details();
//		Display.starting_screen();
//		PreparedStatement users = Menu.registered_users();
			Sign.sign();
		}
//		Sign.sign(users);
		return ps;
	}

//	public static PreparedStatement registered_users() throws SQLException {
////		System.out.println("started");
//		Connection getConnection = Connection_travels.GetConnection();
//		String url = "select mobile,email from users";
//		PreparedStatement ps2 = getConnection.prepareStatement(url);
//		ResultSet set = ps2.executeQuery();
//		while(set.next()) {
////			System.out.println(set.getString(1)+"\t"+set.getString(2));
//		}
//		return ps2;
//	}
//	public static PreparedStatement Tickets_Input() throws SQLException {
//		Connection con = Connection_travels.GetConnection();
//		String url = "select fullName,travelDate,startingplace,destination,fare,age,ticketStatus,bookings,booking1_name,booking1_Number from users;";
//		PreparedStatement pst = con.prepareStatement(url);
//		ResultSet set = pst.executeQuery();
//		while(set.next()) {
////			System.out.println("your name :"+set.getString(1)+"\nyour travel date :"+set.getString(2)+"\nsource :"+set.getString(3)+"\n Destination :"+set.getString(4));
////			System.out.println("Total bill :"+set.getString(5)+"\nage :"+set.getString(6)+"\nticket-status :"+set.getString(7)+"\ntotal bookings :");//+set.getString(8)+"\nbooking name :"+set.getString(9));
////			System.out.println("bookings number :"+set.getString(10));
////			for(int i=1;i<=set.getInt(8);i++) {
////				System.out.println("booking name :"+set.getString(9));
////				System.out.println("bookings number :"+set.getString(10));
////			}
////			System.out.println("thats it !!!");
//		}
//		return pst;
//		
//	}
}
