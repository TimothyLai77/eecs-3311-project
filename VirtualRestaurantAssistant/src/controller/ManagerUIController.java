package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ManagerMain;
import model.ManagerRatings;
import model.ManagerSales;
import view.CartItem;

public class ManagerUIController {
	
	private static ManagerMain mg;
	private static ManagerSales ms;
	private static ManagerRatings rt;
	
	public ManagerUIController() {
		mg = new ManagerMain();
	}
	
	public ManagerUIController(boolean flag) {
		ms = new ManagerSales();
	}
	
	public ManagerUIController(String toggle) {
		rt = new ManagerRatings();
	}
	
	// Submit feedback and rating provided by customers
	public boolean submitFeedback(String orderID, int score, String message) throws SQLException {
		return rt.submitFeedback(orderID, score, message);
	}
	
	// Get the average rating score from db
	public double getAvgRating() throws SQLException {
		return rt.calculateAvgRating();
	}
	
	// Display ratings and feedback
	public String displayRatings() throws SQLException {
		return rt.displayRatings();
	}
	
	//Update sales history methods to manager sales
	public void updateSalesHistory(String id, double total, String date) throws SQLException {
		ms.updateSalesHistory(id, total, date);
	}
	//Add counts to manager sales
	public void addCount(List<CartItem> order) throws SQLException {
		ms.addCount(order);
	}
	//Fetch the favorite
	public String getFavourite() throws SQLException {
		return ms.getFavourite();
	}
	// Add ingredient method
	public String addIngredient(String name, String type, int qty, double price) throws SQLException {
		return mg.addIngredient(name, type, qty, price);
	}
	// Add existing ingredient method
	public String addExistingIngredient(String name, int qty) throws SQLException {
		return mg.addExistingIngredient(name, qty);
	}
	// Delete ingredient method
	public String deleteIngredient(String name) throws SQLException {
		return mg.deleteEntry(name);
	}
	// Update price of ingredient method
	public String updatePrice(String name, double price) throws SQLException {
		return mg.updatePrice(name, price);
	}
	// Views the current inventory
	public ArrayList<ArrayList<String>> viewInventory() throws SQLException{
		return mg.viewInventory();
	}
	// Views the current sales
	public ArrayList<ArrayList<String>> viewSales() throws SQLException {
		return ms.displaySales();
	}
	
	//Checks if account is present
	public boolean checkForExistingAccount() throws SQLException {
		return mg.accountExists();
	}
	
	//Authenticates incoming request
	public boolean AuthenticateManager(String enteredPasscode) throws SQLException {
		String fetchPass = mg.getPasscode();
		if(enteredPasscode.equals(fetchPass)) return true;
		else return false;
	}
	
	public boolean setPasscode(String enteredPasscode) throws SQLException {
		return mg.setPasscode(enteredPasscode);
	}
}
