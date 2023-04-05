package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import view.Components.ErrorPrompt;

public class ManagerMain {

	public static void main(String[] args) throws Exception {
		getConnection();
	}
	
	static Connection con;  // connection to MySQL database
	
	/* COnstructor -- create connection to database */
	public ManagerMain(){
		try{
			getConnection();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Constructor to connect to test database
	public ManagerMain(Connection c) {
		con = c;
	}
	 
	//Establish connection
	public static Connection getConnection() throws Exception{
			try {
				// credentials to access database
				String driver = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://127.0.0.1:3306/3311project";
				String username = "root";
				String password = "root1234";
				
				Class.forName(driver);
				con = DriverManager.getConnection(url,username,password);
				System.out.println("Connected to database!");
				return con;
			}
			catch(SQLException e){
				new ErrorPrompt("<html>No Local Server found. Please start a<br>local MySQL Server to run this app.</html>", true).setVisible(true);
			} catch (Exception e) {
				new ErrorPrompt("<html>Unknown error has occurred</html>", true).setVisible(true);;
			}
			
			return null;
			
		}
	
	//Helper method to return whether an ingredient is present or not
	private boolean ingredientPresent(String name) throws SQLException {
		String command = "SELECT COUNT(*) AS count FROM INGREDIENTS WHERE ingredient_name = '" + name + "'" + ";";
		PreparedStatement st = con.prepareStatement(command);

		int count = -1;
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
		    count = rs.getInt("count");
		}
		
		return count > 0;
	}
	
	/*
	 * HELPER METHOD TO GET THE QUANTITY OF AN INGREDIENT BY NAME
	 * */
	private int getQuantity(String name) throws SQLException{
		
		
		String command = "SELECT quantity FROM INGREDIENTS WHERE ingredient_name ='" + name + "';";
		PreparedStatement st = con.prepareStatement(command);
		ResultSet rs = st.executeQuery(command);
		if (rs.next()) {
		    return rs.getInt("quantity");
		} else {
			return 0;
		}
		
	}
	
	/*
	 * Handles adding an existing ingredient, by updating its quantity.
	 * */
	public String addExistingIngredient(String name, int quantity) throws SQLException {
		
		if(!name.isEmpty() && quantity > 0) {
			String command;
			if(ingredientPresent(name)) {
				int total = quantity + getQuantity(name);
				command = "UPDATE INGREDIENTS SET quantity = " + total + " WHERE ingredient_name = '" + name + "'";
				
				PreparedStatement st = con.prepareStatement(command);

				try {
					int changedRow = st.executeUpdate(command);
					if(changedRow > 0) {
						return "Inventory updated successfully";
					}
				} 
				catch(Exception e) {
					e.printStackTrace();
					return "Failed to add ingredient!";
				}
				
				return "Inventory updated successfully";
			} else {
				return "<html>Ingredient does not exist,<br> please select 'Add Ingredient(s)' above</html>";
			}
		}
		return "Please check your input fields!";
	}
	
	/* On Confirm button click, add specified ingredient to database */
	public String addIngredient(String name, String type, int quantity, double price) throws SQLException {
		
		// all fields must be specified for a new ingredient
		if(!name.isEmpty() && !type.isEmpty() && quantity > 0 && price > 0) {
			
			String command;
			if(!ingredientPresent(name)) {
				command = "INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('" + name + "','" + type + "','" + quantity + "','" + price + "');";
			} else {
				return "<html>Ingredient already exists,<br>please select 'Add Existing Ingredient(s)' above,<br> or if you need to change the price select 'Update price'</html>";
			}
			PreparedStatement st = con.prepareStatement(command);

			try {
				int changedRow = st.executeUpdate(command);
				if(changedRow > 0) {
					return "Inventory updated successfully";
				}
			} 
			catch(Exception e) {
				e.printStackTrace();
				return "Failed to add ingredient!";
			}
		}
		return "Please check your input fields!";
		
	}
	
	/* On Modify button click, update price of the corresponding ingredient in database */
	public String updatePrice(String name, double price) throws SQLException {
		if(!name.isEmpty()) {
			String command = "UPDATE INGREDIENTS SET price = '" + price + "' WHERE ingredient_name = '" + name + "';";
			PreparedStatement st = con.prepareStatement(command);

			try {
				int changedRow = st.executeUpdate(command);
				if(changedRow > 0) return "Inventory successfully updated";
				else return name + " not found";
			}
			catch(Exception e) {
				e.printStackTrace();
				return "Failed to update price! Check your connection.";
			}
		}
		return "Please check your input fields!";
	}
	
	/* On Delete button click, remove the corresponding ingredient from database */
	public String deleteEntry(String name) throws SQLException {
		if(!name.isEmpty()) {
			
			String command = "DELETE FROM INGREDIENTS WHERE ingredient_name = '" + name + "';";
			PreparedStatement st = con.prepareStatement(command);

			try {
				int affected = st.executeUpdate(command);
				if(affected > 0) return name + " deleted successfully";
				else return name + " not found";
			}
			catch(Exception ex) {
				ex.printStackTrace();
				return "Failed to delete entry! Check your connection.";
			}
		}
		return "Please check your input field!";
	}
	
	
	/* If there's any update to the database, display all entries in the inventory */
	public ArrayList<ArrayList<String>> viewInventory() throws SQLException {
		Statement st = con.createStatement();
		String query = "SELECT * FROM INGREDIENTS;";
		ResultSet rs = st.executeQuery(query);
		ArrayList<ArrayList<String>> rows = new ArrayList<>();
		
		while (rs.next()) {
			ArrayList<String> row = new ArrayList<>();
			String name = rs.getString("ingredient_name");
			row.add(name);
			String type = rs.getString("ingredient_type");
			row.add(type);
			String quantity = rs.getString("quantity");
			row.add(quantity);
			String price = rs.getString("price");
			row.add(price);
			rows.add(row);
		}
		
		return rows;
	}
	
	/**
	 * Retrieves the passcode from the manager db
	 * 
	 * @return String - passcode
	 * @throws SQLException
	 */
	public String getPasscode() throws SQLException {
		String command = "SELECT passcode FROM manager;";
		String response = "";
		PreparedStatement st = con.prepareStatement(command);
		ResultSet rs = st.executeQuery(command);
		if(rs.next()) {
			response = 	rs.getString("passcode");
		}
		return response;
	}
	
	/**
	 * Sets the DB passcode value to user selected value
	 * @param passcode
	 * @return whether it was successfully set. Useful in debugging
	 * @throws SQLException
	 */
	public boolean setPasscode(String passcode) throws SQLException {
		String command = "INSERT INTO manager(passcode) VALUES('" + passcode + "');";
		PreparedStatement st = con.prepareStatement(command);
		int affected = st.executeUpdate(command);
		if(affected > 0) return true;
		return false;
	}
	
	/**
	 * Checks whether an account exists inside this.
	 * @return True if exists, false if not.
	 * @throws SQLException
	 */
	public boolean accountExists() throws SQLException {
		return getPasscode().length() != 0;
	}
	
}

