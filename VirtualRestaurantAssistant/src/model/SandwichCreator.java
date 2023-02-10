package model;
import java.util.List;
import java.util.ArrayList;

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
		Inventory inventory = MapInventory.getInstance();
		for(String s : recipe){
			if(!inventory.searchIngredient(s)){
				refundIngredients();
				return null;
			}else{
				prepStation.add(inventory.getIngredient(s)); // add items to prep 
			}
		}
		return prepStation;
	}

	/*
	 * Helper function to return ingredients back to inventory.
	 */
	protected void refundIngredients(){
		Inventory inventory = MapInventory.getInstance();
		for(Ingredient i : prepStation) {
			inventory.putIngredient(i.getName(), i);
		}
		prepStation.clear();
	}
	
}
