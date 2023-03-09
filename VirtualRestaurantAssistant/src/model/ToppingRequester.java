package model;
import java.util.List;
import java.util.ArrayList;
public class ToppingRequester {
	private List<Ingredient> requestedIngredients;
	private Sandwich baseSandwich;
	
	public ToppingRequester(Sandwich sandwich) {
		this.baseSandwich = sandwich;
		this.requestedIngredients = new ArrayList<Ingredient>();
	}
	
	/**
	 * Method Checks that toppings requested exist in the database. 
	 * @param requested, list of lists of each ingredient name requested
	 * @return returns true or false of all the ingredients exist in the inventory
	 */
	public boolean requestToppings(List<String> requested) {
		// check the db and determine if its possible to add the ingredeients
		// returns t/f if ingredeints exist
		
		//todo: SWAP out the map inventroy with the db inventory when that's done
		// DBInventory inventory = DBInventory.getInstance();
		Inventory inventory = DbInventory.getInstance();
		// todo: ^^^ above ^^^
		//make a list of ingredients from the simplifie	d list
		for(String ingredientName : requested){
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
