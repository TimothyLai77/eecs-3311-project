package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public ArrayList<ArrayList<String>> displaySales() throws SQLException {
		Statement st = connection.createStatement();
		String query = "SELECT * FROM ORDERS ORDER BY order_date DESC;";
		ResultSet rs = st.executeQuery(query);
		ArrayList<ArrayList<String>> rows = new ArrayList<>();
		
		while (rs.next()) {
			ArrayList<String> row = new ArrayList<>();
			String id = rs.getString("order_id");
			row.add(id);
			String total = rs.getString("order_total");
			row.add(total);
			String date = rs.getString("order_date");
			row.add(date);
			rows.add(row);
		}
		return rows;
	}
	
	/**
	 * Display up-to-date total sales
	 * */
	public double getTotalSales() throws SQLException {
		String command = "SELECT SUM(order_total) AS total_sales FROM ORDERS;";
		PreparedStatement st = connection.prepareStatement(command);
		ResultSet rs = st.executeQuery();
		double totalSales = 0;
		while(rs.next()) {
			totalSales = rs.getDouble("total_sales");
		}
		
		return totalSales;
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
		String query = "select * from favourites ORDER BY counts DESC LIMIT 3;";
		ResultSet rs = st.executeQuery(query);
		String res = "";
		
		while(rs.next()) {
			String name = rs.getString("sandwichBase");
			String count = rs.getString("counts");
			res += name + " (" + count + " sales)<br>\n";
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

