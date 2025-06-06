package ATM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class Register {
	static Scanner scan = new Scanner(System.in);
	static String username;
	static String pin;

	public static void registerUser() throws SQLException
	{
		Connection con = DBconnection.getConnection();
		
		System.out.println("---User Registration---");
		System.out.println("Enter username:");
		username = scan.nextLine();
		System.out.println("Enter 4-digit pin:");
		pin = scan.nextLine();
		
		String query = "insert into users(username,pin) values(?,?)";
		PreparedStatement pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, username);
		pst.setString(2, pin);
		int rows = pst.executeUpdate();
		ResultSet rs = pst.getGeneratedKeys();
		rs.next();
		System.out.println("rows affected:"+rows);
		
		System.out.println("Registered Successfully\nYour Account ID is "+rs.getInt(1));
		
		
	}
}
