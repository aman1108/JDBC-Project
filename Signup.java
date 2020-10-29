
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Signup {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://Localhost:3306/test";
	static final String USER = "Username";
	static final String PASS = "Password";
	
	static Integer idval =109;
	public void sign(Scanner scan){
		Connection conn = null;
		Statement stmt = null;
		try{
		      //STEP 1: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 2: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 3: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      
		      System.out.println("Sign Up Page");
		      
		      try {
		    	  
		    	  
                  String name = "";
                  String password = "";
                  String mail = "";
		    	  System.out.println("Enter Your Name");
		    	  name = scan.nextLine();
		    	  
		    	  System.out.println("Enter Your Password");
		    	  password = scan.nextLine();
		    	  
		    	  System.out.println("Enter your Mail");
		    	  mail = scan.nextLine();
		    	  
		    	  idval=idval+1;
		    	  String sql = "INSERT INTO user VALUES " + "("+idval+ ", " + "'" + password + "', " + "'" + name + "', " + "'" + mail + "')";
		    	  stmt.execute(sql);
		    	  System.out.println("Success!");
		    	  System.out.println("Your id is:");
		    	  System.out.println(idval);
		      }
		      catch (Exception e)
		      {
		    	  System.out.println(e);
		      }
		      
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		         
		         //Inform user of error
		         System.out.println("Error closing connection to the database!");
		      }//end finally try
		   }//end try

		
	}
}
