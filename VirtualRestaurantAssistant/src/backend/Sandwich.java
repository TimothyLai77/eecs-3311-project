package backend;

import java.util.List;

public abstract class Sandwich {
	protected List<Ingredient> ingredientList;
	protected String sandwichName;
	protected String description;
	
	
	public double getCost() {
	        double cost = 0;
	        for (Ingredient ingredient : ingredientList) {
	            cost += ingredient.getPrice();
	        }
	        return cost;
	  }
	 
	 public List<Ingredient> getIngredientList() {
	        return ingredientList;
	 }
	
	 public String getName() {
	        return sandwichName;
	 } 
	

	 public String getDescription() {
	        return description;
	    }
}
