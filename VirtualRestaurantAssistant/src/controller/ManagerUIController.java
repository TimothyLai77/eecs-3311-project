package controller;

import java.sql.SQLException;

import model.ManagerMain;

public class ManagerUIController {
	
	private static ManagerMain mg;
	
	public ManagerUIController(){
		mg = new ManagerMain();
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
