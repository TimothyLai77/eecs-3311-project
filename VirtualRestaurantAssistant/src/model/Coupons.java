package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class manages coupon functionalities in the Manager view.
 * <p>
 * 	Only one coupon can be activated at a time. 
 * </p>
 * */
public class Coupons {
	
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
	 * @param discountValue		the coupon String value in percentage to be deleted.
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
	
	/**
	 * Activate a coupon value.
	 * Toggle on the coupon feature. 
	 * <p>Also, deactivate all other coupon values that are not the current coupon.</p>
	 * @param discount		String percentage discount value.
	 * */
	public boolean activateCoupon(String discount) throws SQLException {
		double value = (double) (Double.parseDouble(discount) / 100);
		if(couponExists(value)) {
			String command1 = "update coupon set isActive = 1 where discountValue = " + value + ";";
			String command2 = "update coupon set isActive = 0 where discountValue != " + value + ";";
			try {
				PreparedStatement st = connection.prepareStatement(command1);
				PreparedStatement st2 = connection.prepareStatement(command2);
				st.executeUpdate(command1);
				st2.executeUpdate(command2);
				return true;
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Get the currently active coupon value to calculate the new order total.
	 * @return double
	 * */
	public double getActiveCoupon() throws SQLException{
		String command = "select discountValue from coupon where isActive = 1;";
		double ret = 0.0;
		PreparedStatement st = connection.prepareStatement(command);
		ResultSet res = st.executeQuery(command);
		while(res.next()) {
			ret = res.getDouble("discountValue");
		}
		if(ret == 0.0) {
			return 0.0;
		}
		return ret;
	}
	
	/**
	 * Toggle off the coupon feature. Deactivate all available coupons.
	 * */
	public void disableCoupons() throws SQLException {
		String command = "update coupon set isActive = 0;";
		PreparedStatement st = connection.prepareStatement(command);
		st.executeUpdate(command);
	}
	
	/**
	 * Display available coupon
	 * */
	public ArrayList<ArrayList<String>> displayCoupons() throws SQLException {
		String command = "select * from coupon;";
		PreparedStatement st = connection.prepareStatement(command);
		ArrayList<ArrayList<String>> ret = new ArrayList<>();
		ResultSet set = st.executeQuery(command);
		while(set.next()) {
			ArrayList<String> row = new ArrayList<>();
			double value = set.getDouble("discountValue");
			String isActive = set.getString("isActive");
			row.add(""+ value*100);
			row.add(isActive);
			ret.add(row);
		}
		
		return ret;
	}
}
