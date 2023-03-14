package model;

import model.Ingredients.Ingredient;


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
	 * 
	 * @param:- ingredientName
	 * ingredientName is a key.
	 * 
	 * @return:- quantity 
	 * returns available quantity of the ingredient 
	 */
	public abstract int checkQuantity(String ingredientName);

}