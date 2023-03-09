package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import view.CartItem;

public class ManagerSales {
	
	public static Connection connection;  // connection to database
	
	public static void main(String[] args) {
		ManagerSales sales = new ManagerSales();
		try {
			System.out.println(sales.getFavourite());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
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
	
	// constructor to set Connection to test database
	public ManagerSales(Connection con) {
		connection = con; 
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
	
	/**
	 * Add counts of sandwich bases into database
	 * */
	public void addCount(List<CartItem> bases) throws SQLException {
		Statement st = connection.createStatement();
		for(CartItem item : bases) {
			if(!baseExists(item.getName())) {
				String command = "INSERT INTO favourites values('" + item.getName() + "','" + item.getQuantity() + "');";
				st.executeUpdate(command);
			}
			else {
				String com = "UPDATE favourites SET counts = counts + " + item.getQuantity() + " WHERE sandwichBase = '" + item.getName() + "';";
				st.executeUpdate(com);
			}
		}
	}
	
	/**
	 * Search for existing sandwich base count
	 * */
	public boolean baseExists(String name) throws SQLException {
		ArrayList<String> bases = new ArrayList<>();
		Statement st = connection.createStatement();
		String query = "select sandwichBase from favourites;";
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			String baseName = rs.getString("sandwichBase");
			bases.add(baseName);
		}
		
		if(bases.isEmpty()) {
			return false;
		}
		else if (bases.contains(name)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Return the list of counts of all bases
	 * */
	public String getCounts() throws SQLException {
		Statement st = connection.createStatement();
		String query = "select * from favourites ORDER BY counts DESC;";
		ResultSet rs = st.executeQuery(query);
		String res = "";
		
		while(rs.next()) {
			String name = rs.getString("sandwichBase");
			String count = rs.getString("counts");
			res += name + ": " + count + "\n";
		}
		
		return res;
	}
	
	/**
	 * Return the most popular / most ordered base
	 * */
	public String getFavourite() throws SQLException {
		 Statement st = connection.createStatement();
	        String query = "SELECT sandwichBase FROM favourites WHERE counts = (SELECT MAX(counts) FROM favourites)";

	        ResultSet rs = st.executeQuery(query);
	        String result = "";

	        if(rs.next()){
	            result = rs.getString("sandwichBase");
	        }

	        return result;
	}
}

