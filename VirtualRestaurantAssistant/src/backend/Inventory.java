package backend;

import java.util.*;


public interface Inventory {

	
	/**
	 * By this way we use the key to get the linked list containing the ingredient 
	 * and then we poll the ingredient in front of the list ie: head element
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 * 
	 * return the first item of list in the map and also remove it.
	 */
	public abstract Ingredient getIngredient(String ingredientName);
	
	/**
	 * this function checks whether a ingredient is in the inventory.
	 * input:- ingredientName 
	 * output:- boolean value accordingly
	 * 
	 * checks if the list is populated 
	 */
	
	public abstract boolean searchIngredient(String ingredientName);
	
	/**
	 * this methods removes ingredient list on position of the key in the map and returns removed value  
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 * 
	 */
	
	public abstract List<Ingredient> removeAllIngredients(String ingredientName);
	
	/**
	 * this methods puts specific ingredients in the map, the new ingredient will be added at the end of linked list
	 * input:- ingredientName , ingredient 
	 * output:- boolean
	 */
	public abstract boolean putIngredient(String ingredientName,Ingredient i);
	
	/**
	 * this method adds new ingredient in the map
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 */
	public abstract List<Ingredient> addIngredient(String ingredientName,LinkedList<Ingredient> list);
	

}