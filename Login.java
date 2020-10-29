
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://Localhost:3306/test";
	static final String USER = "Username";
	static final String PASS = "password";

	public void log(Scanner scan){
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
		      
		      System.out.println("Login Page");
		      
		      try  {
		    	  
		    	  System.out.println("Enter User ID");
		    	  String input = scan.nextLine();
		    	  int idv=0;
	              try{
	            	   idv=Integer.valueOf(input);
	               }
	              catch(Exception e)
	               {
	            	   System.out.println("UserId is Integer");
	               }
	              System.out.println("Enter Password");
		    	  String Password = scan.nextLine();
		    	  
		    	  String sql = "SELECT Pass FROM user where Id="+idv+"";
		    	  try (ResultSet rs = stmt.executeQuery(sql)) {
                      
                      while(rs.next()){
                          //Retrieve by column name
                          String title = rs.getString("Pass");
                          System.out.println(title);
                          //Display values
                          if (title.equals(Password))
                          {
                        	  System.out.println("Successful Login and redirecting to details page");
                        	  User U =new User();
                        	  U.fun(scan, idv);
                          }
                          else
                          {
                        	  System.out.println("INcorrect Password");
                          }
                        
                      }
                      
                      //Close the result set
                      rs.close();
                  }
                  catch(Exception e) {
                       e.printStackTrace();
                       System.out.println("Error! Invalid Username");
                  }
		    	  
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
