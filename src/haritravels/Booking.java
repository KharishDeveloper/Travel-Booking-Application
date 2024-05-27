package haritravels;

import java.io.IOException;
import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Booking extends Sign {

	static String htt;

	public static PreparedStatement date() throws NullPointerException, SQLException, IOException {
		try {
			String GetMobileNumber = Sign.MobileNumber;
			if (GetMobileNumber == null) {
				System.out.println("it seems like a you are not a existed user !!!");
				Menu.menu();
			}
			LocalDate Todaydate = LocalDate.now();
			System.out.println("today date is :" + Todaydate);
			System.out.println("enter the details of your journey");
			System.out.println("Enter the date :[YYYY-MM-DD]");
			String Date = Constants.sc.next();
			LocalDate TravelDate = LocalDate.parse(Date);
			long Days = ChronoUnit.DAYS.between(Todaydate, TravelDate);
			while (Days < 1) {
//				System.out.println("this is the point to update !!!");
				System.out.println("please enter the valid date");
//				System.out.println("you entered expired date");
				Date = Constants.sc.nextLine();
				TravelDate = LocalDate.parse(Date);
				Days = ChronoUnit.DAYS.between(Todaydate, TravelDate);

			}
			System.out.println("your journey date is :" + TravelDate);
			DayOfWeek WeekValue = TravelDate.getDayOfWeek();
			bus_details();
			System.out.println("enter the source :");
			String Source = Constants.sc.next();
			System.out.println("enter the destination :");
			String Destination = Constants.sc.next();
			System.out.println("here available buses");
			Connection connection = Constants.GetConnection();
			String Query1 = "select * from buses where (startplace,destination)=(?,?);";
			PreparedStatement ps1 = connection.prepareStatement(Query1);
			ps1.setString(1, Source);
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
//				System.out.println("---------------------------------");
				System.out.print(rs.getColumnName(3) + " : \t" + result1.getString(3));
				System.out.print("\t\t");
				System.out.print(rs.getColumnName(4) + " : \t" + result1.getString(4));
				System.out.print("\t");
				System.out.println(" ");
				System.out.print(rs.getColumnName(5) + " : \t" + result1.getString(5));
				System.out.print("\t");
				System.out.println(" ");
				System.out.println("how many tickets you want to book in HARITRAVELS");
				int Bookings = Constants.sc.nextInt();
				if (Bookings <= 5) {
					System.out.println("getting availability");
					int AvailableSeats = result1.getInt(5);
					if (AvailableSeats <= Bookings) {// 5->seats

						System.out.println("not availabile to book the tickets");
						System.out.println("we could not process the booking on this particular ticket number !!!");
						System.out.println("application terminated !!!");
						Exit.repeat();

					} else {
						System.out.println("available");
						int ActualAmount = result1.getInt(4) * Bookings;
						System.out.println("Ticket price :\t" + ActualAmount);
						int TaxAmount = ActualAmount / 18;
						System.out.println("taxes :\t\t " + TaxAmount);
						int FareAmount = ActualAmount + TaxAmount;
//				System.out.println("val :"+ Week_value.getValue());
						int RemainingSeats = result1.getInt(5) - Bookings;
						if (WeekValue.getValue() > 5) {
//					System.out.println("week-end");
							ActualAmount = (ActualAmount + 200) * Bookings;
//							System.out.println("only taxes :" + TaxAmount);
							System.out.println("Total price :\t" + FareAmount);
//				System.out.println("remaining seats :"+vacancy);
							String Query2 = "update buses set seats=? where (startplace,destination)=(?,?);";
							PreparedStatement ps2 = connection.prepareStatement(Query2);
							ps2.setInt(1, RemainingSeats);
							ps2.setString(2, Source);
							ps2.setString(3, Destination);
							ps2.executeUpdate();
//				System.out.println("update :"+update);
//					System.out.println("vacancy :"+vacancy);
//				System.out.println("successfully updated !!!");
						} else {
//					System.out.println("update soon for week days");
//					System.out.println(" week day");
//					System.out.println("remaining tickets are :"+vacancy);
//							System.out.println("only taxes :" + TaxAmount);
							System.out.println("Total price :\t" + FareAmount);// (fare_amount+(org_amount*booking)) +"
																				// /-
																				// "+"only");
							String Query3 = "update buses set seats=? where (startplace,destination)=(?,?);";
							PreparedStatement ps2 = connection.prepareStatement(Query3);
							ps2.setInt(1, RemainingSeats);
							ps2.setString(2, Source);
							ps2.setString(3, Destination);
							ps2.executeUpdate();
						}
						if (Bookings > 0) {// 4
							System.out.println("enter fullname :");
							String FullName = Constants.sc.next();
							System.out.println("enter mobile number :");
							String PassengerMobileNumber = Constants.sc.next();
							System.out.println("Enter age :");
							int PassengerAge1 = Constants.sc.nextInt();
//							System.out.println("bookings is/are :" + Bookings);
							System.out.println("HTTSId [generated id] is : " + Change.GenerateHttsID());
							String Query4 = "insert into details(mobile,presentDate, travelDate, startingplace, destination, taxes, fare, age, ticketStatus, bookings, HTTS) values(?,curdate(),?,?,?,?,?,?,?,?,?);";
							PreparedStatement pst1 = connection.prepareStatement(Query4);
							pst1.setString(1, GetMobileNumber);
							pst1.setString(2, Date);
							pst1.setString(3, Source);
							pst1.setString(4, Destination);
							pst1.setFloat(5, TaxAmount);
							pst1.setInt(6, FareAmount);
							pst1.setInt(7, PassengerAge1);
							pst1.setString(8, "success");
							pst1.setInt(9, Bookings);
							pst1.setString(10, Change.HTTSId);
//						pst1.setString(9, full_name);
//						pst1.setString(10, mobile_number_for_booking_details);
							pst1.execute();
//							System.out.println(q1);
//						System.out.println("booking 1 ");
//						System.out.println("updated rows :"+q1);

							String Query5 = "insert into bookings(HTTS_ID, bookings, booking1_name, booking1_number ,booking1_age, mobile) values(?,?,?,?,?,?);";
							PreparedStatement pst2 = connection.prepareStatement(Query5);
							pst2.setString(1, Change.HTTSId);
							pst2.setInt(2, Bookings);
							pst2.setString(3, FullName);
							pst2.setString(4, PassengerMobileNumber);
							pst2.setInt(5, PassengerAge1);
							pst2.setString(6, GetMobileNumber);
							pst2.execute();
//							System.out.println("your transaction is sucess");
							Bookings--; // value 3

//						System.out.println(bookings+"in booking1 data");
//						System.out.println("confirming ");
//						System.out.println("");
//						System.out.println("selected the booking 1");
						}

//					String url1 = "update details set HTTS=? where mobile=?;";
////					String url1 ="insert into bookings(mobile,HTTS_ID) values(?,?);";
//					PreparedStatement ps2 = Connection_travels.GetConnection().prepareStatement(url1);
//					ps2.setString(1, Change.id);
//					ps2.setString(2, login);
//					boolean b = ps2.execute();
//						System.out.println("executed !!!");

						// write a insert query on bookings [mobile];
						// after write a update query...

//					System.out.println("system");
//					while(bookings>=3) { 0>=3
//						System.out.println("hello");
//						if(bookings==3) {
//							System.out.println("selected the bookings 4");
//							bookings--;
//						}
//						else if(bookings==4) {
//							System.out.println("selected the bookings 5");
//							bookings--;
//						}
					}
//			System.out.println("\t bookings value :-" + bookings );
					while (Bookings >= 3) { // 3 >= 3
						if (Bookings == 3) { // 3 == 3
							System.out.println("enter fullname :");
							String FullName = Constants.sc.next();
							System.out.println("enter mobile number :");
							String PassengerMobileNumber = Constants.sc.next();
							System.out.println("Enter age :");
							int passengerAge4 = Constants.sc.nextInt();
							System.out.println("bookings is/are :" + Bookings);
							String Query6 = "update bookings set booking4_name=?,booking4_Number=?,booking4_age=? where HTTS_ID=?;";
//				String url5 = "insert into bookings(booking4_name,booking4_Number) values(?,?);";
							PreparedStatement ps2 = connection.prepareStatement(Query6);
							ps2.setString(1, FullName);
							ps2.setString(2, PassengerMobileNumber);
							ps2.setInt(3, passengerAge4);
							ps2.setString(4, Change.HTTSId);
							ps2.executeUpdate();
//				System.out.println(i +"latest update !!!");
//				System.out.println("selected the bookings 4/4");
							Bookings--;
						} else if (Bookings == 4) { // 4 == 4
							System.out.println("enter fullname :");
							String FullName = Constants.sc.next();
							System.out.println("enter mobile number :");
							String PassengerMobileNumber = Constants.sc.next();
							System.out.println("Enter age :");
							int passengerAge5 = Constants.sc.nextInt();
							System.out.println("bookings is/are :" + Bookings);
							String Query7 = "update bookings set booking5_name=?,booking5_Number=?,booking5_age=? where HTTS_ID=?;";
							PreparedStatement ps2 = connection.prepareStatement(Query7);
							ps2.setString(1, FullName);
							ps2.setString(2, PassengerMobileNumber);
							ps2.setInt(3, passengerAge5);
							ps2.setString(4, Change.HTTSId);
							ps2.executeUpdate();
//				System.out.println(i +"latest update !!!");
//				System.out.println("selected the bookings 4/5");
//				System.out.println("selected the bookings 5");
							Bookings--;
						}
//		}
					}
//	}
					if (Bookings <= 2) { // 3<=2
						while (Bookings > 1) { // 1>=1
							if (Bookings == 2) {
								System.out.println("enter fullname :");
								String FullName = Constants.sc.next();
								System.out.println("enter mobile number :");
								String PassengerMobileNumber = Constants.sc.next();
								System.out.println("Enter age :");
								int passengerAge2 = Constants.sc.nextInt();
								System.out.println("bookings is/are :" + Bookings);
								String Query8 = "update bookings set booking2_name=?,booking2_Number=?,booking2_age=? where HTTS_ID=?;";
								PreparedStatement ps2 = connection.prepareStatement(Query8);
								ps2.setString(1, FullName);
								ps2.setString(2, PassengerMobileNumber);
								ps2.setInt(3, passengerAge2);
								ps2.setString(4, Change.HTTSId);
								ps2.executeUpdate();
//							System.out.println(i +"latest update !!!");
//							System.out.println("selected the booking 2");
								Bookings--;// 1
							}
							if (Bookings == 1) {
								System.out.println("enter fullname :");
								String FullName = Constants.sc.next();
								System.out.println("enter mobile number :");
								String PassengerMobileNumber = Constants.sc.next();
								System.out.println("Enter age :");
								int passengerAge3 = Constants.sc.nextInt();
								System.out.println("bookings is/are :" + Bookings);
								String Query9 = "update bookings set booking3_name=?,booking3_Number=?,booking3_age=? where HTTS_ID=?;";
								PreparedStatement ps2 = connection.prepareStatement(Query9);
								ps2.setString(1, FullName);
								ps2.setString(2, PassengerMobileNumber);
								ps2.setInt(3, passengerAge3);
								ps2.setString(4, Change.HTTSId);
								ps2.executeUpdate();
//								System.out.println(i +"latest update !!!");
//								System.out.println("selected the booking 3");
								Bookings--;
							}
						}
						if (Bookings == 1) {
							System.out.println("enter fullname :");
							String FullName = Constants.sc.next();
							System.out.println("enter mobile number :");
							String PassengerMobileNumber = Constants.sc.next();
							System.out.println("Enter age :");
							int passengerAge2 = Constants.sc.nextInt();
							System.out.println("bookings is/are :" + Bookings);
							String Query10 = "update bookings set booking2_name=?,booking2_Number=?,booking2_age=? where HTTS_ID=?;";
							PreparedStatement ps2 = connection.prepareStatement(Query10);
							ps2.setString(1, FullName);
							ps2.setString(2, PassengerMobileNumber);
							ps2.setInt(3, passengerAge2);
							ps2.setString(4, Change.HTTSId);
							ps2.executeUpdate();
//							System.out.println("selected the booking 4/2 ");
//							System.out.println(i +"\n  + latest update !!!");
							Bookings--;
							System.out.println(htt);
							System.out.println("your transaction is sucess");

						}
					}
//					while(bookings>=3) {	// 2 >= 3
//						if(bookings==3) {	//3==3
//							System.out.println("enter fullname :");
//							String full_name = scanner_dates.next();
//							System.out.println("enter mobile number :");
//							String mobile_number_for_booking_details = scanner_dates.next();
//							System.out.println("bookings is/are :"+bookings);
//							String url5 = "update users set booking4_name=?,booking4_Number=? where mobile=?;";
//							PreparedStatement ps2 = con1.prepareStatement(url5);
//							ps2.setString(1, full_name);
//							ps2.setString(2, mobile_number_for_booking_details);
//							ps2.setString(3, mobile_number_login);
//							int i = ps2.executeUpdate();
////							System.out.println(i +"latest update !!!");
//							System.out.println("selected the bookings 4/4");
//							bookings--;
//						}
//						else if(bookings==4) {	// 2 == 4
//							System.out.println("selected the bookings 5");
//							bookings--;
//						}
////					}
//			}
//					Login.login_details();
					Travelstatus.checktravelstatus();
				}

				else {
					System.out.println("unable to book the more tickets !!!");
					System.out.println("change the booking number and try again !!");
					Booking.date();
				}
			} else {
				System.out.println("not allow to book the more tickets !!!");
				System.out.println("try the booking with another source and destination ");
				Booking.date();
			}
//			}
			Constants.sc.close();
		} catch (InputMismatchException e) {
			System.out.println("enter the numerically only");
			Booking.date();
		} catch (NullPointerException e) {
			System.out.println("enter carefully !!!");
			Booking.date();
		} catch (Exception q) {
			System.out.println("enter valid date !!!");
			Booking.date();
		}
		return null;

	}

	public static void bus_details() throws SQLException {
		Connection connection = Constants.GetConnection();
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
		}
	}
}
