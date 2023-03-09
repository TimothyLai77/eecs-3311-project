package model;
import java.util.List;

import model.Ingredients.Ingredient;

import java.util.ArrayList;


/*
 * abstract class with a method that gathers ingredients for a sandwich. 
 */

public abstract class SandwichCreator {

	protected List<String> recipe; 
	protected List<Ingredient> prepStation;
	public abstract Sandwich createSandwich();
	
	/**
	 * Method attempts to gather all ingredients from the inventory.
	 * @return null if inventory can not fulfill recipe, else return List<Ingredient>
	 */
	protected List<Ingredient> gatherIngredients(){
		prepStation = new ArrayList<Ingredient>();

		// use the inventory
		Inventory inventory = DbInventory.getInstance();
		
		// loop through each ingredient in the sandwich's recipe and check the inventory if
		// that ingredient exists
		for(String s : recipe){
			if(!inventory.searchIngredient(s)){
				return null;
			}else{
				prepStation.add(inventory.getIngredient(s)); // add items to prep 
			}
		}
		return prepStation;
	}


	
}
