package controller;

import java.util.LinkedList;

import javax.swing.UIManager;

import model.Beef;
import model.Bread;
import model.Chicken;
import model.Ingredient;
import model.Inventory;
import model.MapInventory;
import view.HomePage;

public class Main {

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {

		// look and feel setting for javaq swing.
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		// Gets the current inventory instance --- FROM SINGLETON CLASS
		Inventory inventory = MapInventory.getInstance();
		
		// Generates a collection of Ingredient Type
		LinkedList<Ingredient> beefs = new LinkedList<>();
		LinkedList<Ingredient> breads = new LinkedList<>();
		LinkedList<Ingredient> meatball = new LinkedList<>();
		LinkedList<Ingredient> chicken = new LinkedList<>();
		
		//Populates our Inventory with the ingredient lists above.
		for(int i = 0 ; i < 6 ; i++) {
			beefs.add(new Beef("Beef", 10.0, "Meat"));
			breads.add(new Bread("Bread", 5.0, "Bread"));
			meatball.add(new Bread("Meatball", 2.0, "Meatball"));
			chicken.add(new Chicken("Chicken", 12.0, "Meat"));
		}
		
		//Respective lists are pushed to the inventory instance.
		inventory.addIngredient("Bread", breads);
		inventory.addIngredient("Beef", beefs);
		inventory.addIngredient("Meatball", meatball);
		inventory.addIngredient("Chicken", chicken);
		
		// Calls the HomePage essentially the APP starting flow.
		HomePage home = new HomePage();
		home.setVisible(true);
		
	}

}
