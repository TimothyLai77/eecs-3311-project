package model;
import java.util.List;

public class ToppingRequester {
	private List<Ingredient> requestedIngredients;
	private Sandwich baseSandwich;
	
	public ToppingRequester(Sandwich sandwich) {
		this.baseSandwich = sandwich;
	}
	
	public boolean requestToppings(List<List<String>> requested) {
		// check the db and determine if its possible to add the ingredeients
		// returns t/f if ingredeints exist
		
		// parse input string. 
		
		
		return false;
	}
	
	/**
	 * Method that applies the requested toppings to the base sandwich
	 * @return returns a sandwich with toppings.
	 */
	public Sandwich applyToppings() {
		// pass in the base sandwich to a constructor that 
		SandwichWithToppings sandwichWithToppings = new SandwichWithToppings(baseSandwich);
		//TODO: call the DBINventory here...
		
		sandwichWithToppings.addToppings(this.requestedIngredients);
		return sandwichWithToppings;
	}


	
	
}
