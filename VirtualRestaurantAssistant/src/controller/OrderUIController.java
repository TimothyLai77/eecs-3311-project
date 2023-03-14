package controller;
import model.BeefSandwichCreator;
import model.ChickenSandwichCreator;
import model.MeatballSandwichCreator;
import model.Sandwich;
import model.SandwichCreator;
import model.ToppingRequester;
import model.VeggiepattySandwichCreator;
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
		else if(name.equals("Meatball")) {
			return new MeatballSandwichCreator();
		}		
		else if(name.equals("Veggiepatty")) {
			return new VeggiepattySandwichCreator();
		}
		else {
			return null;
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
			// select a sandwich creatora

			SandwichCreator sandwichCreator = setSandwichCreator(item.getName());
	
			// make quantity amount of sandwiches per item
			for(int i = 0; i < item.getQuantity(); i++) {
				Sandwich sandwich = sandwichCreator.createSandwich();
				if(sandwich != null) {
					// if the sandwich can be made add the toppings
					ToppingRequester toppingRequester = new ToppingRequester(sandwich);
					List<String> toppingsForThisSandwich = item.getAddedOptions();

					if(toppingRequester.requestToppings(toppingsForThisSandwich)){
						// if toppings exist make the sandiwch with toppings
						Sandwich sandwichWithToppings = toppingRequester.applyToppings();
						orderBag.add(sandwichWithToppings);
					}
					// otherwise don't add anything to the bag, and UI will show error
				} 
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
