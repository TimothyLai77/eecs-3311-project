package controller;

import java.util.LinkedList;

import javax.swing.UIManager;


import ingredients.*;
import model.Ingredient;
import model.Inventory;
import model.MapInventory;

import view.HomePage;

public class Main {

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {

		// look and feel setting for java swing.
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		// Gets the current inventory instance --- FROM SINGLETON CLASS
//		Inventory inventory = MapInventory.getInstance();
//		
//		// Generates a collection of Ingredient Type
//		LinkedList<Ingredient> beefs = new LinkedList<>();
//		LinkedList<Ingredient> breads = new LinkedList<>();
//		LinkedList<Ingredient> meatball = new LinkedList<>();
//		LinkedList<Ingredient> chicken = new LinkedList<>();
//		LinkedList<Ingredient> tomato = new LinkedList<>();
//		LinkedList<Ingredient> lettuce = new LinkedList<>();
//		LinkedList<Ingredient> cheddar = new LinkedList<>();
//		
//		//Populates our Inventory with the ingredient lists above.
//		for(int i = 0 ; i < 100 ; i++) {
//			beefs.add(new Beef("Beef", 10.0, "Meat"));
//			breads.add(new Bread("Bread", 5.0, "Bread"));
//			meatball.add(new Bread("Meatball", 2.0, "Meatball"));
//			chicken.add(new Chicken("Chicken", 12.0, "Meat"));
//			tomato.add(new Tomato("Tomato", 5, "veggie"));
//			lettuce.add(new Lettuce("Lettuce", 12, "veggie"));
//			cheddar.add(new Cheddar("Cheddar", 3.25, "cheese"));
//		}
//		
//		//Respective lists are pushed to the inventory instance.
//		inventory.addIngredient("Bread", breads);
//		inventory.addIngredient("Beef", beefs);
//		inventory.addIngredient("Meatball", meatball);
//		inventory.addIngredient("Chicken", chicken);
//
//		inventory.addIngredient("Tomato", tomato);
//		inventory.addIngredient("Lettuce", lettuce);
//		inventory.addIngredient("Cheddar", cheddar);
		
		// Calls the HomePage essentially the APP starting flow.
		HomePage home = new HomePage();
		home.setVisible(true);
	}

}
