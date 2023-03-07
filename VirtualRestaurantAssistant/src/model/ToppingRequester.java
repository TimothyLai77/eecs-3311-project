package model;
import java.util.List;
import java.util.ArrayList;
public class ToppingRequester {
	private List<Ingredient> requestedIngredients;
	private Sandwich baseSandwich;
	
	public ToppingRequester(Sandwich sandwich) {
		this.baseSandwich = sandwich;
	}
	
	/**
	 * Method Checks that toppings requested exist in the database. 
	 * @param requested, list of lists of each ingredient name requested
	 * @return returns true or false of all the ingredients exist in the inventory
	 */
	public boolean requestToppings(List<List<String>> requested) {
		// check the db and determine if its possible to add the ingredeients
		// returns t/f if ingredeints exist
		
		// simplify list
		List<String> simplifiedList = simplifyInputList(requested);
		
		
		//todo: remove when db is implemented.
		
		
		//DBInventory inventory = DBInventory.getInstance();

		MapInventory inventory = MapInventory.getInstance();
		//make a list of ingredients from the simplifie	d list
		for(String ingredientName : simplifiedList){
			// check if ingredient exists
			if(!inventory.searchIngredient(ingredientName)){
				return false; // not all ingredients exist
			}else{
				Ingredient i = inventory.getIngredient(ingredientName);
				requestedIngredients.add(i);
			}
		}
		return true; // all ingredients exist in db, can proceed to add toppings
	}
	
	/**
	 * Private helper method to simplify the input list to a single list of strings
	 * @param requested
	 * @return
	 */
	private List<String> simplifyInputList(List<List<String>> requested){
		List<String> simplifiedList = new ArrayList<String>();
		// simplify data to a single list,
		for(List<String> sublist : requested){
			for(String topping : sublist){
				simplifiedList.add(topping);
			}
		}
		return simplifiedList;
	}

	/**
	 * Method that applies the requested toppings to the base sandwich
	 * @return returns a sandwich with toppings.
	 */
	public Sandwich applyToppings() {
		// pass in the base sandwich to a constructor that 
		SandwichWithToppings sandwichWithToppings = new SandwichWithToppings(baseSandwich);
		
		sandwichWithToppings.addToppings(this.requestedIngredients);
		return sandwichWithToppings;
	}
}
