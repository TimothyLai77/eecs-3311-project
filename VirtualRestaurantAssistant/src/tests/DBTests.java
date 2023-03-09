package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class establishes a new connection to the test database.
 * Please check the login credentials for this test database in the getConnection() method. 
 * */
public class DBTests {

	public static void main(String[] args) {
		try {
			getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static Connection con;  // connection to MySQL database
	
	public DBTests () {
		try {
			getConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Establish connection
	public static Connection getConnection() throws Exception{
			try {
				// credentials to access database
				String driver = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://127.0.0.1:3306/3311test";
				String username = "root";
				String password = "root1234";
				
				Class.forName(driver);
				con = DriverManager.getConnection(url,username,password);
				System.out.println("Connected to database!");
				return con;
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			return null;
			
		}

	/**
	 * Clear the test database for test cases
	 * @throws SQLException 
	 * */
	public void clearDatabase() throws SQLException {
		if(con != null) {
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();
			st1.executeUpdate("delete from orders;");
			st2.executeUpdate("delete from favourites;");
		}
	}
}
