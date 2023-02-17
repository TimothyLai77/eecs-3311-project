package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerSales {
	
	static Connection connection;  // connection to database
	
	// get connection
	public ManagerSales() {
		try {
			connection = ManagerMain.getConnection();
			System.out.println("Connected successfully!");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/** 
	 * Update sales history in database
	 * */
	public void updateSalesHistory(String orderID, double total, String date ) throws SQLException {
		Statement st = connection.createStatement();
		String command = "INSERT INTO ORDERS(order_total, order_id, order_date) VALUES('" + total + "','" + orderID + "','" + date + "');";
		try {
			st.executeUpdate(command);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Display sales history 
	 * */
	public String displaySales() throws SQLException {
		Statement st = connection.createStatement();
		String query = "SELECT * FROM ORDERS;";
		ResultSet rs = st.executeQuery(query);
		String orderSt = "Order ID";
		String amountSt = "Amount";
		String dateSt = "Date";
		String columns = String.format("%-22s %-24s %-22s\n", orderSt, amountSt, dateSt);
		String separator = "--";
		String ret = columns + separator.repeat(33) + "\n";
		
		while (rs.next()) {
			String id = rs.getString("order_id");
			String total = rs.getString("order_total");
			String date = rs.getString("order_date");
			
			String format = String.format("%-22s $%-22s %-22s\n", id, total, date);
			ret += format;
		}
		
		return ret;
	}
	
	/**
	 * Display up-to-date total sales
	 * */
	public String getTotalSales() throws SQLException {
		String returnRes = "$";
		Statement st = connection.createStatement();
		String command = "SELECT SUM(order_total) AS total_sales FROM ORDERS;";
		ResultSet rs = st.executeQuery(command);
		
		while(rs.next()) {
			returnRes += rs.getString("total_sales");
		}
		
		return returnRes;
	}
}
