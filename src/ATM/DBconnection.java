package ATM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	private static final String url = "jdbc:mysql://localhost:3306/atm";
	private static final String username = "root";
	private static final String password = "Divya#123!";
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url,username,password);
	}
}
