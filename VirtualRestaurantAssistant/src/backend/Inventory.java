package backend;

import java.util.*;


public class Inventory {
	HashMap<String,LinkedList<Ingredient>> inventory ;
	private static Inventory instance;
	
	private Inventory() {
	inventory =  new HashMap<String,LinkedList<Ingredient>>();
	}
	
	
	/**
	 * Gets an instance of the inventory system. 
	 * @return the Inventory instance
	 */
	public static Inventory getIntsance() {
		if(Inventory.instance == null) {
			Inventory.instance = new Inventory();
		}
		return Inventory.instance ;
	}

	/**
	 * By this way we use the key to get the linked list containing the ingredient 
	 * and then we poll the ingredient in front of the list ie: head element
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 * 
	 * return the first item of list in the map and also remove it.
	 */
	public Ingredient getIngredient(String ingredientName){
		return this.inventory.get(ingredientName).poll();
	}
	
	/**
	 * this function checks whether a ingredient is in the inventory.
	 * input:- ingredientName 
	 * output:- boolean value accordingly
	 * 
	 * checks if the list is populated 
	 */
	
	public boolean searchIngredient(String ingredientName) {
		if(this.inventory.get(ingredientName)==null) {
			return false;
		}
		
		return this.inventory.get(ingredientName).size()>0;
	}
	
	/**
	 * this methods removes ingredient list on position of the key in the map and returns removed value  
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 * 
	 */
	
	public List<Ingredient> removeAllIngredients(String ingredientName) {
		return inventory.remove(ingredientName);
	}
	
	/**
	 * this methods puts specific ingredients in the map, the new ingredient will be added at the end of linked list
	 * input:- ingredientName , ingredient 
	 * output:- boolean
	 */
	public boolean putIngredient(String ingredientName,Ingredient i) {
		return this.inventory.get(ingredientName).add(i);
	}
	
	/**
	 * this method adds new ingredient in the map
	 * input:- ingredientName 
	 * output:- List<ingredient>
	 */
	public List<Ingredient> addIngredient(String ingredientName,LinkedList<Ingredient> list){
		return inventory.putIfAbsent(ingredientName, list);
	}
	

}