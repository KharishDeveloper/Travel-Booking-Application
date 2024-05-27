package haritravels;

//variable done
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class Display {

	public static void StartingScreen() throws IOException, NullPointerException, SQLException {
		try {
			FileInputStream FileLogo = new FileInputStream(Constants.Logo);// loading the file
			int LogoReader;
			while ((LogoReader = FileLogo.read()) != -1) {

				System.out.print((char) LogoReader);
			}
			System.out.println(" ");
			System.out.println("\n");

			try {
				System.out.println("select an option :");
				System.out.println("1.New user registration \t 2.Login account \n 3.Exit ");
				String UserChoice;
				UserChoice = Constants.sc.next();
				switch (UserChoice) {
				case "1":
					Menu.menu();
					break;
				case "2":
					Sign.sign();
					break;
				case "3":
					Exit.repeat();
					break;
				default:
					System.out.println("select the valid option !!!");
					System.out.println(" ");
					StartingScreen();
				}
			} catch (InputMismatchException e) {
				System.out.println("You must enter the value in numeric only !");
//		System.out.println("you have to enter just re-launch the application ");
				Display.StartingScreen();
			}
			FileLogo.close();
		} catch (FileNotFoundException e) {
			System.out.println("Logo not loaded in your device !!!");
			System.out.println("You just drop a mail. Our team will get back to you !!!");
			System.out.println("Sorry for this interruption");
		}
	}
}