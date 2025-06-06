package ATM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Check 
{
	
	public static void checkUserLogin(int id,String pin,int lock) throws SQLException
	{
		Connection con = DBconnection.getConnection();
		String query = "select * from users where id=? and pin=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		pst.setString(2, pin);
		ResultSet rs = pst.executeQuery();
		if(rs.next())
		{
			System.out.println("Logged in Successfully");
			PostLogin.loggingIn(id);
		}
		else{
			lock--;
			if(lock>0)
			{
				System.out.println("Wrong Account ID or pin.\n"+lock+" attempts left");
				Login.loginUser(lock);
			}
			else {
				
				System.out.println("Too many attempts. Try again later");
			}
			
		}
	}
}
