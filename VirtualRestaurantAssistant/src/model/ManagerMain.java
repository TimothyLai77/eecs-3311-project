package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			return null;
			
		}
	
	
	/* On Confirm button click, add specified ingredient to database */
	public String addIngredient(String name, String type, int quantity, double price) throws SQLException {
		// all fields must be specified for a new ingredient
		if(!name.isEmpty() && !type.isEmpty() && quantity > 0 && price > 0) {
			Statement st = con.createStatement();
			String commmand = "INSERT INTO INGREDIENTS(ingredient_name, ingredient_type, quantity, price) VALUES('" + name + "','" + type + "','" + quantity + "','" + price + "');";
			try {
				st.executeUpdate(commmand);
			}
			catch(Exception e) {
				e.printStackTrace();
				return "Failed to add ingredient!";
			}
			return name + " added successfully";
		}
		return "Please check your input fields!";
	}
	
	/* On Modify button click, update quantity of the corresponding ingredient in database */
	public String modifyInventoryQuantity(String name, int quantity) throws SQLException {
		if(!name.isEmpty()) {
			Statement st = con.createStatement();
			String command = "UPDATE INGREDIENTS SET quantity = '" + quantity + "' WHERE ingredient_name = '" + name + "';";
			try {
				st.executeUpdate(command);
			}
			catch(Exception e) {
				e.printStackTrace();
				return "Failed to modify quantity!";
			}
			return name + " modified successfully!";
		}
		return "Please check your input fields!";
	}
	
	/* On Modify button click, update price of the corresponding ingredient in database */
	public String modifyInventoryPrice(String name, double price) throws SQLException {
		if(!name.isEmpty()) {
			Statement st = con.createStatement();
			String command = "UPDATE INGREDIENTS SET price = '" + price + "' WHERE ingredient_name = '" + name + "';";
			try {
				st.executeUpdate(command);
			}
			catch(Exception e) {
				e.printStackTrace();
				return "Failed to modify quantity!";
			}
			return name + " modified successfully!";
		}
		return "Please check your input fields!";
	}
	
	/* On Modify button click, update both quantity and price of the corresponding ingredient in database */
	public String modifyInventoryBoth(String name, int quantity, double price) throws SQLException {
		if(!name.isEmpty()) {
			Statement st = con.createStatement();
			String command = "UPDATE INGREDIENTS SET price = '" + price + "', quantity = '" + quantity + "' WHERE ingredient_name = '" + name + "';";
			try {
				st.executeUpdate(command);
			}
			catch(Exception e) {
				e.printStackTrace();
				return "Failed to modify ingredient!";
			}
			return name + " modified successfully!";
		}
		return "Please check your input fields!";
	}
	
	/* On Delete button click, remove the corresponding ingredient from database */
	public String deleteEntry(String name) throws SQLException {
		if(!name.isEmpty()) {
			Statement st = con.createStatement();
			String command = "DELETE FROM INGREDIENTS WHERE ingredient_name = '" + name + "';";
			try {
				st.executeUpdate(command);
			}
			catch(Exception ex) {
				ex.printStackTrace();
				return "Failed to delete entry!";
			}
			return name + " deleted successfully";
		}
		return "Please check your input field!";
	}
	
	/* If there's any update to the database, display all entries in the inventory */
	public String viewInventory() throws SQLException {
		Statement st = con.createStatement();
		String query = "SELECT * FROM INGREDIENTS;";
		ResultSet rs = st.executeQuery(query);
		String ret = "";
		
		while (rs.next()) {
			String name = rs.getString("ingredient_name");
			String type = rs.getString("ingredient_type");
			String quantity = rs.getString("quantity");
			String price = rs.getString("price");
			
			String format = String.format("%-30s %-30s %-30s $%-30s \n", name, type, quantity, price);
			ret += format;
		}
		
		return ret;
	}
	
}

