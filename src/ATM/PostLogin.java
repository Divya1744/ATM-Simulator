package ATM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PostLogin {
	
	static Scanner scan = new Scanner(System.in);
	public static void loggingIn(int id) throws SQLException
	{
		
		System.out.println("---ATM Menu---");
		System.out.println("1.View Account Details\n2.Deposit\n3.Withdraw\n4.Logout");
		System.out.println("Enter your choice:");
		int ch = scan.nextInt();
		while(ch!=4)
		{
			switch(ch)
			{
				case 1:
					checkBal(id);
					break;
				case 2:
					deposit(id);
					break;
				case 3:
					withdraw(id);
					break;
					
			
			}
			
			System.out.println("Enter your choice:");
			ch = scan.nextInt();
		}
		System.out.println("Logged Out Successfully !");
		
	}
	
	public static void checkBal(int id) throws SQLException
	{
		System.out.println("account details");
		Connection con = DBconnection.getConnection();
		String query = "select balance from users where id=?";
	    PreparedStatement pst = con.prepareStatement(query);
	    pst.setInt(1, id);
	    ResultSet rs = pst.executeQuery();
	    rs.next();
	    System.out.println("Your current Balance: "+rs.getInt(1));
	}
	
	public static void deposit(int id) throws SQLException
	{
		System.out.println("deposit");
		Connection con = DBconnection.getConnection();
		System.out.println("Deposit amount: ");
		int deposit = scan.nextInt();
		String query = "update users set balance = balance + ? where id = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, deposit);
		pst.setInt(2, id);
		int rows = pst.executeUpdate();
		
		String query2 = "insert into transaction (acc_id,type,amount) values(?,?,?)";
		pst = con.prepareStatement(query2);
		pst.setInt(1, id);
		pst.setString(2,"deposit");
		pst.setInt(3, deposit);
		int rows2 = pst.executeUpdate();
		System.out.println("Successfully deposited"+rows+rows2);
		
	}
	
	public static void withdraw(int id) throws SQLException
	{
		Connection con = DBconnection.getConnection();
		System.out.println("Enter the amount:");
		int amt = scan.nextInt();
		String query = "select balance from users where id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		rs.next();
		if(rs.getInt(1)<amt)
		{
			System.out.println("Insufficient Amount");
		}
		else {
			String query2 = "update users set balance = balance - ? where id = ?";
			pst = con.prepareStatement(query2);
			pst.setInt(1, amt);
			pst.setInt(2, id);
			int rows = pst.executeUpdate();
			
			String query3 = "insert into transaction (acc_id,type,amount) values(?,?,?)";
			pst = con.prepareStatement(query3);
			pst.setInt(1, id);
			pst.setString(2,"withdraw");
			pst.setInt(3, amt);
			int rows2 = pst.executeUpdate();
			System.out.println("Amount Withdrawn successfully"+rows+rows2);
			
		}
	}
}
