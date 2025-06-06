package ATM;
import java.sql.SQLException;
import java.util.*;
public class Home {

	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		int ch=0;
	    do{
	    	System.out.println("---Welcome to ATM Simulator---\n");
			System.out.println("1.Register\n2.Login\n3.Exit");
			System.out.print("Enter your choice:");
		    ch = scan.nextInt();
		    
	    	if(ch==1)
	    	{
	    		Register.registerUser();
	    	}
	    	else if(ch==2)
	    	{
	    		Login.loginUser(3);
	    	}
	    	
	    }while(ch!=3);
	    System.out.println("Thank You for visiting !");
	    scan.close();

	}

}
