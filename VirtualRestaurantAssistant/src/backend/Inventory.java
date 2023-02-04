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
	 * this methods removes all ingredients from the map and returns removed value  
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 * 
	 */
	public List<Ingredient> removeAllIngredients(String ingredientName) {
		return inventory.remove(ingredientName);
	}
	
	/**
	 * this methods puts specific ingredients in the map and returns updated list  
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 * 
	 */
	public List<Ingredient> putIngredient(String ingredientName,Ingredient i) {
		inventory.get(ingredientName).add(i);
	return inventory.get(ingredientName);
	}
	
	/**
	 * this method adds new ingredient in the map
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 */
	public List<Ingredient> addIngredient(String ingredientName,List<Ingredient> list){
		return inventory.putIfAbsent(ingredientName, list);
	}
	
}