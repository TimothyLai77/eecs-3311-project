package controller;

import java.sql.SQLException;
import java.util.List;

import model.ManagerMain;
import model.ManagerSales;
import view.CartItem;

public class ManagerUIController {
	
	private static ManagerMain mg;
	private static ManagerSales ms;
	
	public ManagerUIController() {
		mg = new ManagerMain();
	}
	
	public ManagerUIController(boolean flag) {
		ms = new ManagerSales();
		
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
	public String viewInventory() throws SQLException{
		return mg.viewInventory();
	}
}
