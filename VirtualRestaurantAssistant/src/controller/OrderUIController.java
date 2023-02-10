package controller;
import model.BeefSandwichCreator;
import model.ChickenSandwichCreator;
import model.MeatballSandwichCreator;
import model.Sandwich;
import model.SandwichCreator;
import view.CartItem;

import java.util.List;
import java.util.ArrayList;

public class OrderUIController {
	
	/**
	 * Private method used to get the appropriate Sandwich Creator 
	 * @param name of sandwich creator needed
	 * @return a sandwichCreator object 
	 */
	private static SandwichCreator setSandwichCreator(String name) {
		if(name.equals("Chicken")) {
			return new ChickenSandwichCreator();
		}
		else if(name.equals("Beef")) {
			return new BeefSandwichCreator();
		}
		else {
			return new MeatballSandwichCreator();
		}
	}
	
	/**
	 * Goes through the cart and attempts to build the sandwich for each 
	 * @param carList list of cartItems
	 * @return List of prices 
	 */
	public static List<Double> getSandwichOrder(List<CartItem> cartList) {
		List<Sandwich> orderBag = new ArrayList<>();
		for(CartItem item : cartList) {
			// select a sandwich creator
			SandwichCreator sandwichCreator = setSandwichCreator(item.getName());
			// make quantity amount of sandwiches per item
			for(int i = 0; i < item.getQuantity(); i++) {
				Sandwich sandwich = sandwichCreator.createSandwich();
				if(sandwich != null) {
					orderBag.add(sandwich);
				} // check if factory actually made a sandwich, and don't add the null return value
			}
		}
		
		// populate list of prices
		List<Double> costs = new ArrayList<>();
		for(Sandwich sandwich : orderBag) {
			costs.add(sandwich.getCost());
		}

		
		return costs;
	}
	

}
