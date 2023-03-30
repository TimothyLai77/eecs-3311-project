package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Coupons;
import model.ManagerMain;
import model.ManagerRatings;
import model.ManagerSales;
import view.Components.CartItem;

public class ManagerUIController {
	
	private static ManagerMain mg;
	private static ManagerSales ms;
	private static ManagerRatings rt;
	private static Coupons coupon;
	
	public ManagerUIController() {
		mg = new ManagerMain();
	}
	
	public ManagerUIController(boolean flag) {
		ms = new ManagerSales();
	}
	
	public ManagerUIController(String toggle) {
		rt = new ManagerRatings();
	}
	
	public ManagerUIController(double anyDouble) {
		coupon = new Coupons();
	}
	
	public String addCoupon(String percentValue) throws SQLException {
		return coupon.addCoupon(percentValue);
	}
	
	public String removeCoupon(String percentValue) throws SQLException {
		return coupon.removeCoupon(percentValue);
	}
	
	public boolean activateCoupon(String percentValue) throws SQLException {
		return coupon.activateCoupon(percentValue);
	}
	
	/**
	 * Randomly decide whether to return the coupon discount value or not.
	 * <p>Probability of getting the discount is 70%.</p>
	 * */
	public double getActiveCoupon() throws SQLException {
		Random random = new Random();
		int decision = random.nextInt(10) + 1;
		if(decision >= 4) {
			return coupon.getActiveCoupon();
		}
		return 0.0;
	}
	
	public void disableCoupons() throws SQLException {
		coupon.disableCoupons();
	}
	
	public ArrayList<ArrayList<String>> displayCoupons() throws SQLException {
		return coupon.displayCoupons();
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
	//Fetch total sales
	public double getTotalSales() throws SQLException {
		return ms.getTotalSales();
	}
	//Fetches sales breakdown
	public String getSalesBreakdown() throws SQLException {
		return ms.getCounts();
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
