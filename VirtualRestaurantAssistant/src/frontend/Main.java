package frontend;

import java.util.LinkedList;

import backend.Beef;
import backend.Bread;
import backend.Chicken;
import backend.Ingredient;
import backend.Inventory;
import backend.MapInventory;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		Inventory inventory = MapInventory.getInstance();
		LinkedList<Ingredient> beefs = new LinkedList<>();
		LinkedList<Ingredient> breads = new LinkedList<>();
		LinkedList<Ingredient> meatball = new LinkedList<>();
		LinkedList<Ingredient> chicken = new LinkedList<>();
		
		for(int i = 0 ; i < 6 ; i++) {
			beefs.add(new Beef("Beef", 10.0, "Meat"));
			breads.add(new Bread("Bread", 5.0, "Bread"));
			meatball.add(new Bread("Meatball", 2.0, "Meatball"));
			chicken.add(new Chicken("Chicken", 12.0, "Meat"));
		}

		inventory.addIngredient("Bread", breads);
		inventory.addIngredient("Beef", beefs);
		inventory.addIngredient("Meatball", meatball);
		inventory.addIngredient("Chicken", chicken);
		
		HomePage home = new HomePage();
		home.setVisible(true);
		
	}

}
