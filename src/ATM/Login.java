package ATM;

import java.sql.SQLException;
import java.util.*;
public class Login 
{
	static int id;
	static String pin;
	static Scanner scan = new Scanner(System.in);
	public static void loginUser(int lock) throws SQLException
	{
		System.out.println("Enter Account ID:");
		id = scan.nextInt();
		System.out.println("Enter pin:");
		pin = scan.next();
		
		Check.checkUserLogin(id,pin,lock);
		
	}
}
