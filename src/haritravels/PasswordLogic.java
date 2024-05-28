package haritravels;

public class PasswordLogic {

	public static String PasswordMaintainingLogic(String password) {
		int cap=0, small=0;
		for (int k = 0; k <= 1; k++) {
			if ((password.length() >= 8 &&  password.length() <= 20) == true) {
			System.out.println("length satisfied");
				if (password.contains("1") || password.contains("2")
						|| password.contains("3") || password.contains("4")
						|| password.contains("5") || password.contains("6")
						|| password.contains("7") || password.contains("8")
						|| password.contains("9") || password.contains("0")) {
					if (password.contains("@") || password.contains("#")
							|| password.contains("$")) {
						int vall = 0;
						int[] asciiValue = new int[password.length()];
						for (int i = 0; i < password.length(); i++) {
							char val = password.charAt(i);
							vall = (int) val;
							asciiValue[i] = vall;
						}
						for (int j = 0; j <= asciiValue.length - 1; j++) {
					if (asciiValue[j] >= 97 && asciiValue[j] <= 122) {
								small++;
							} else {
							}
							if (asciiValue[j] >= 65 && asciiValue[j] <= 90) {
//						System.out.println("consists of capital letter(s)");
								cap++;
//						System.out.println(UserService.checkerValueCap + "password checker capital");
							} else {
//						System.out.println("not consists of capital letter");
							}

						}
					} else {
						System.out.println("not contains a special character");
						password = Constants.sc.next();
						k = 0;
					}

				} else {
					System.out.println("Not contain a digit");
					password = Constants.sc.next();
					k = 0;
				}
			} else {
				System.out.println("length not satsified");
				password = Constants.sc.next();
				k = 0;
			}
		}

		if ((cap >= 1 && small >= 1) == true)
		{
			System.out.println("password successfully validated");
			System.out.println(password);
			
			return password;
		} else {
			System.out.println("unable to validate your password try again later!!!");
			System.out.println("could not find either capital or small letter");
			

			return "0";
		}
	}
}