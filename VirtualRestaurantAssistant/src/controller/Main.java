package controller;

import java.util.LinkedList;

import javax.swing.UIManager;

import model.Inventory;
import model.MapInventory;
import model.Ingredients.Beef;
import model.Ingredients.Bread;
import model.Ingredients.Cheddar;
import model.Ingredients.Chicken;
import model.Ingredients.Ingredient;
import model.Ingredients.Lettuce;
import model.Ingredients.Tomato;
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
		// MapInventory inventory = MapInventory.getInstance();

		// // Generates a collection of Ingredient Type
		// LinkedList<Ingredient> beefs = new LinkedList<>();
		// LinkedList<Ingredient> breads = new LinkedList<>();
		// LinkedList<Ingredient> meatball = new LinkedList<>();
		// LinkedList<Ingredient> chicken = new LinkedList<>();
		// LinkedList<Ingredient> tomato = new LinkedList<>();
		// LinkedList<Ingredient> lettuce = new LinkedList<>();
		// LinkedList<Ingredient> cheddar = new LinkedList<>();
		// LinkedList<Ingredient> mayo = new LinkedList<>();
		// LinkedList<Ingredient> ketchup = new LinkedList<>();
		// LinkedList<Ingredient> american = new LinkedList<>();
		// LinkedList<Ingredient> veggiePatty = new LinkedList<>();

		// // Populates our Inventory with the ingredient lists above.
		// for (int i = 0; i < 100; i++) {
		// 	beefs.add(new Beef("Beef", 6.9, "Meat"));
		// 	breads.add(new Bread("Bread", 3, "Bread"));
		// 	meatball.add(new Bread("Meatball", 0.75, "Meatball"));
		// 	chicken.add(new Chicken("Chicken", 4.25, "Meat"));
		// 	tomato.add(new Tomato("Tomato", 1.25, "veggie"));
		// 	lettuce.add(new Lettuce("Lettuce", 3.10, "veggie"));
		// 	cheddar.add(new Cheddar("Cheddar", 5, "cheese"));
		// 	mayo.add(new Lettuce("Mayo", 0.75, "sauce"));
		// 	american.add(new Cheddar("American", 2.50, "cheese"));
		// 	veggiePatty.add(new Cheddar("Veggiepatty", 5, "vegetable"));
		// 	ketchup.add(new Cheddar("Ketchup", 0.50, "sauce"));
		// }

		// // Respective lists are pushed to the inventory instance.
		// inventory.addIngredient("Bread", breads);
		// inventory.addIngredient("Beef", beefs);
		// inventory.addIngredient("Meatball", meatball);
		// inventory.addIngredient("Chicken", chicken);
		// inventory.addIngredient("Tomato", tomato);
		// inventory.addIngredient("Lettuce", lettuce);
		// inventory.addIngredient("Cheddar", cheddar);
		// inventory.addIngredient("American", american);
		// inventory.addIngredient("Mayo", mayo);
		// inventory.addIngredient("Veggiepatty", veggiePatty);
		// inventory.addIngredient("Ketchup", ketchup);


		// Calls the HomePage essentially the APP starting flow.
		HomePage home = new HomePage();
		home.setVisible(true);
	}

}

