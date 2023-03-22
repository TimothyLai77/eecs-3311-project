package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class manages coupon functionalities in the Manager view.
 * <p>
 * 	Only one coupon can be activated at a time. 
 * </p>
 * */
public class Coupons {

	public static void main(String[] args) {
		Coupons cp = new Coupons();
		try {
			System.out.println(cp.removeCoupon("25"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static Connection connection;
	
	// get connection to production database
	public Coupons() {
		try {
			connection = ManagerMain.getConnection();
			System.out.println("Connected successfully!");
		}	
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// get connection to test database
	public Coupons(Connection c) {
		connection = c;
	}
	
	/**
	 * Add a discount coupon to the database. Param value must be between 10 and 100.
	 * @param discount	String percentage eg: 20, 15, 50.
	 * @return String message.
	 * */
	public String addCoupon(String discount) throws SQLException {
		if(!discount.isEmpty()) {
			if(Integer.parseInt(discount) > 100 || Integer.parseInt(discount) < 10) {
				return "value must be between 10 and 100!";
			}
			Double value = (double) (Double.parseDouble(discount) / 100);
			String command = "insert into coupon(discountValue) values('" + value + "');";
			PreparedStatement st = connection.prepareStatement(command);
			try {
				st.executeUpdate(command);
				return "Coupon added successfully!";
			}
			catch(Exception ex) {
				return "This discount value is already in the database!";
			}
		}
		
		return "Input field is empty!";
	}
	
	/**
	 * Remove a coupon from database.
	 * @param discountValue		the coupon String value to be deleted.
	 * @return String message.
	 * */
	public String removeCoupon(String discountValue) throws SQLException {
		if(!discountValue.isEmpty()) {
			Double value = (double) (Double.parseDouble(discountValue) / 100);
			String command = "delete from coupon where discountValue='" + value + "';";
			PreparedStatement st = connection.prepareStatement(command);
			if(!couponExists(value)) {
				return "This discount value is NOT in the database!";
			}
			st.executeUpdate(command);
			return "Coupon removed successfully!";
		}
		
		return "Input field is empty!";
	}
	
	/**
	 * Check whether a coupon value is in the database.
	 * */
	private boolean couponExists(double value) throws SQLException {
		String query = "select count(*) as count from coupon where discountValue='" + value + "';";
		PreparedStatement st = connection.prepareStatement(query);
		int count = -1;
		ResultSet set = st.executeQuery(query);
		while(set.next()) {
			count = set.getInt("count");
		}
		
		return count > 0;
	}

}
