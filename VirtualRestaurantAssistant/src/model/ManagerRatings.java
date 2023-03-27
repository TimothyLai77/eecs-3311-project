package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class handles interactions with the database in terms of customers' ratings and feedback.
 * */
public class ManagerRatings {
	
	public static Connection con;	// connection to database
	
	public static void main(String[] args) {
		ManagerRatings rt = new ManagerRatings();
		try {
			System.out.println(rt.displayRatings());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// get connection to production database
	public ManagerRatings() {
		try {
			con = ManagerMain.getConnection();
			System.out.println("Connected successfully!");
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// get connection to test database
	public ManagerRatings(Connection c) {
		con = c;
	}
	
	/**
	 * Send the customer feedback to database.
	 * 
	 * @param  orderID	 orderID associated with the Cart's orderID
	 * @param  score	 int score from 1 to 5 given by the customer
	 * @param  message	 optional text from customer
	 * @throws SQLException 
	 * */
	public boolean submitFeedback(String orderID, int score, String message) throws SQLException {
		String command = "";
		
		if(score < 1) {
			return false;
		}
		
		if(message.isEmpty()) {
			command = "INSERT INTO ratings(score, RORder_id) values('" + score + "','" + orderID + "');";
		}
		else {
			command = "INSERT INTO ratings values('" + score + "','" + message + "','" + orderID + "');";
		}
		
		PreparedStatement st = con.prepareStatement(command);
		try {
			st.executeUpdate(command);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Calculate and return the average rating score from database.
	 * */
	public double calculateAvgRating() throws SQLException {
		String query = "select avg(score) as avg from ratings;";
		PreparedStatement st = con.prepareStatement(query);
		ResultSet res = st.executeQuery(query);
		double avg = 0.0;
		
		while(res.next()) {
			avg = res.getDouble("avg");
		}
		return avg;
	}
	
	/**
	 * Display all customers' ratings and feedback
	 * */
	public String displayRatings() throws SQLException {
		String query = "select * from ratings;";
		PreparedStatement st = con.prepareStatement(query);
		ResultSet res = st.executeQuery(query);
		String ret = "OrderID\tRating\tFeedback\n";
		ret += "--".repeat(15) + "\n";
		
		while(res.next()) {
			String id = res.getString("ROrder_id");
			String score = res.getString("score");
			String feedback = res.getString("feedback");
			ret += id + "\t" + score + "\t" + feedback + "\n";
		}
		
		return ret;
	}

}
