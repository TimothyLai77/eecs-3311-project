package backend;

import java.util.*;


public class Inventory {
	Map<String,List<Ingredient>> inventory ;
	
	public Inventory() {
	inventory =  new HashMap<String,List<Ingredient>>();
	}
	
	//this function returns the list of ingredients
	/**
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 * 
	 */
	public List<Ingredient> getIngredient(String ingredientName){
		return inventory.get(ingredientName);
	}
	
	/**
	 * this function checks whether a ingredient is in the inventory.
	 * input:- ingredientName 
	 * output:- boolean value accordingly
	 * 
	 */
	
	public boolean searchIngredient(String ingredientName) {
		return inventory.containsKey(ingredientName);
	}
	
	/**
	 * this methods removes ingredients from the map and returns removed value  
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 * 
	 */
	public List<Ingredient> removeIngredients(String ingredientName) {
		return inventory.remove(ingredientName);
	}
	
}