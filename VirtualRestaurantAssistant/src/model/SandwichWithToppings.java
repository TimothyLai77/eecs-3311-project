package model;

import java.util.List;

import model.Ingredients.Ingredient;

public class SandwichWithToppings extends Sandwich {
	
	protected Sandwich baseSandwich;
	protected String name;
	protected double cost;
	protected String description;

	//todo: idk if this should really be a final int/double
	private static final double SURCHARGE_PER_INGREDIENT = 0.50; 
	private static final int INGREDIENTS_BEFORE_SURCHARGE = 5;
	private double surcharge;

	protected List<Ingredient> ingredientList;
	public SandwichWithToppings(Sandwich baseSandwich) {
		this.baseSandwich = baseSandwich;
		this.name = baseSandwich.getName();
		this.cost = baseSandwich.getCost();
		this.description = baseSandwich.getDescription();
		this.surcharge = 0.0;
	}
	
	
	/**
	 * Method that adds new toppings to the list 
	 * @param toppingsToAdd
	 */
	public void addToppings(List<Ingredient> toppingsToAdd) {
		ingredientList = baseSandwich.getIngredientList();
		for(Ingredient newIngredient : toppingsToAdd){
			ingredientList.add(newIngredient);
		}
	}

	public double getSurcharge(){
		if(ingredientList.size() > INGREDIENTS_BEFORE_SURCHARGE){
			int surchargedIngredients = ingredientList.size() - INGREDIENTS_BEFORE_SURCHARGE;
			this.surcharge = surchargedIngredients *SURCHARGE_PER_INGREDIENT;
		}
		return this.surcharge;
	}

	public double getCost(){
		double cost = 0;
		for(Ingredient ingredient : this.ingredientList){
			cost += ingredient.getPrice();
		}
		return cost;
	}

	public List<Ingredient> getIngredientList(){
		return ingredientList;
	}

	public String getName(){
		return this.baseSandwich.getName() + " with toppings.";
	}

	public String getDescription(){
		return this.baseSandwich.getDescription() + " with toppings.";
	}
	
	
	
	
}
