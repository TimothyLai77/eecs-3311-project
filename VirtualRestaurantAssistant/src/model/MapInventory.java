package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MapInventory implements Inventory{
	private HashMap<String,LinkedList<Ingredient>> inventory;
	private static MapInventory instance;
	
	/**
	 * Creates a new inventory hashMap which has ingredient name as key and for value it will have 
	 * objects of ingredients stored in the linked list .
	 */
	private MapInventory() {
		inventory =  new HashMap<String,LinkedList<Ingredient>>();
	}
	
	/**
	 * 
	 * @return This methods returns the reference to the only object of the class
	 * if it is null then create a new instance 
	 */
	public static MapInventory getInstance() {
		if(MapInventory.instance == null) {
			MapInventory.instance = new MapInventory();
		}
		return MapInventory.instance;
	}
	

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
	public Ingredient getIngredient(String ingredientName){
		return this.inventory.get(ingredientName).poll();
	}
	
	/**
	 * This function checks whether a ingredient is in the inventory. checks if the list is populated 
	 * 
	 * @param :- ingredientName
	 * ingredientName is a key.
	 * 
	 *@return :- boolean value
	 *returns a boolean value accordingly ie: if the ingredient is in the map 
	 */
	
	public boolean searchIngredient(String ingredientName) {
		if(this.inventory.get(ingredientName)==null) {
			return false;
		}
		
		return this.inventory.get(ingredientName).size()>0;
	}
	

	/**
	 * This methods removes ingredient list on position of the key in the map and returns removed value  
	 * @param :- ingredientName
	 * ingredientName is a key.
	 * 
	 * @return:- List<Ingredient>
	 * a list of ingredients that is removed from the map
	 * 
	 */
	
	public List<Ingredient> removeAllIngredients(String ingredientName) {
		return inventory.remove(ingredientName);
	}
	
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
	public boolean putIngredient(String ingredientName,Ingredient i) {
		return this.inventory.get(ingredientName).add(i);
	}
	
	/**
	 * This method adds new type of ingredient in the map
	 * @param:- ingredientName 
	 * ingredientName is a key.
	 * 
	 * @return:- List<ingredient>
	 * returns new list added in the map
	 */
	public List<Ingredient> addIngredient(String ingredientName,LinkedList<Ingredient> list){
		return inventory.putIfAbsent(ingredientName, list);
	}
	
	
	/**
	 * 
	 * @param:- ingredientName
	 * ingredientName is a key.
	 * 
	 * @return:- quantity 
	 * returns available quantity of the ingredient 
	 */
	
	public int checkQuantity(String name) {
		return inventory.get(name).size();
	}
	

	
}
