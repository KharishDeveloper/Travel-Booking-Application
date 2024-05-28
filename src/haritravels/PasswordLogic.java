package haritravels;

public class PasswordLogic {
//	public static void main(String[] args) {
		

//	PasswordMaintainingLogic();
//}

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
//				System.out.println("contains digit");
					if (password.contains("@") || password.contains("#")
							|| password.contains("$")) {
//					System.out.println("contains special character");
						int vall = 0;
						int[] asciiValue = new int[password.length()];
						for (int i = 0; i < password.length(); i++) {
							char val = password.charAt(i);
//						System.out.println(val + " charat function");
							vall = (int) val;
//						System.out.println("ascii " + vall);
							asciiValue[i] = vall;
						}
//					for (int qw : asciiValue) {
//						System.out.println(qw + "qw");
//					}
						for (int j = 0; j <= asciiValue.length - 1; j++) {
//					System.out.println(asciiValue[j]+"value of "+j);
							if (asciiValue[j] >= 97 && asciiValue[j] <= 122) {
								// 72 >65 && 72 <90
//						System.out.println("consists of small letter(s)");
								small++;
////						System.out.println(UserService.checkerValueSmall + " password checker small");
							} else {
//						System.out.println("not consists of small letters");

							}
							if (asciiValue[j] >= 65 && asciiValue[j] <= 90) {
//						System.out.println("consists of capital letter(s)");
								cap++;
//						System.out.println(UserService.checkerValueCap + "password checker capital");
							} else {
//						System.out.println("not consists of capital letter");
							}

//						System.out.println("capital val" + UserServiceCreation.checkerValueCap);
//						System.out.println(UserServiceCreation.checkerValueCap >= 1);
//						System.out.println("small val" + UserServiceCreation.checkerValueSmall);
//						System.out.println(UserServiceCreation.checkerValueSmall >= 1);
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
//			password=password;
			System.out.println(password);
			// creation data inserting.
//		UserCreationDB.AccountCreation(Constants.GetConnection());
			// insert row for login table
//		AccountDetailsDB.loginInsert(Constants.GetConnection());
//		allocating a row for transaction operations
//		TransactionOpsDB.TransactionOpsInsert(Constants.GetConnection());
			// go to login page

//		while((UserServiceCreation.Deposit>=10 && UserServiceCreation.Deposit<=2000)==false) {
//			System.out.println("entered while deposit ");
//		}
//		System.out.println("its safe zone !!!");
//		
//		allocating bank account details
//		AccountDetails.ops();

//		BankLoginLogics.loginlogics();

//		System.out.println("cap "+UserServiceCreation.checkerValueCap);
			// cap=1 small=2
			return password;
		} else {
			System.out.println("unable to validate your password try again later!!!");
			System.out.println("could not find either capital or small letter");
//			password = "0";
			

			return "0";
		}
	}
}
//}