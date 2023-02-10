package model;

import java.util.*;


public interface Inventory {

	
	/**
	 * By this way we use the key to get the linked list containing the ingredient 
	 * and then we poll the ingredient in front of the list ie: head element
	 * 
	 * @param :- ingredientName
	 * ingredientName is a key. 
	 * 
	 * @return :- Ingredient 
	 * return the first item of list in the map associated with the key ingredientName and also remove it.
	 */
	public abstract Ingredient getIngredient(String ingredientName);
	
	/**
	 * This function checks whether a ingredient is in the inventory. checks if the list is populated 
	 * 
	 * @param :- ingredientName
	 * ingredientName is a key.
	 * 
	 *@return :- boolean value
	 *returns a boolean value accordingly ie: if the ingredient is in the map 
	 */
	
	public abstract boolean searchIngredient(String ingredientName);
	
	
	/**
	 * This methods removes ingredient list on position of the key in the map and returns removed value  
	 * @param :- ingredientName
	 * ingredientName is a key.
	 * 
	 * @return:- List<Ingredient>
	 * a list of ingredients that is removed from the map
	 * 
	 */
	
	public abstract List<Ingredient> removeAllIngredients(String ingredientName);
	
	
	/**
	 * This methods puts specific ingredients in the map, the new ingredient will be added at the end of linked list
	 * @param:- ingredientName
	 *  ingredientName is a key.
	 *  
	 * @param:- Ingredient i
	 * i is a ingredient to be added 
	 *
	 * @return:- boolean value
	 * returns a boolean value accordingly ie: on successful addition of the ingredient it returns true else it returns false
	*/
	public abstract boolean putIngredient(String ingredientName,Ingredient i);
	
	/**
	 * This method adds new type of ingredient in the map
	 * @param:- ingredientName 
	 * ingredientName is a key.
	 * 
	 * @return:- List<ingredient>
	 * returns new list added in the map
	 */
	public abstract List<Ingredient> addIngredient(String ingredientName,LinkedList<Ingredient> list);
	
	/**
	 * 
	 * @param:- ingredientName
	 * ingredientName is a key.
	 * 
	 * @return:- quantity 
	 * returns available quantity of the ingredient 
	 */
	public abstract int checkQuantity(String ingredientName);

}