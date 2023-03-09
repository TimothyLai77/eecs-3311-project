package model;

/*
 * Concrete creator for sandwich with toppings. It takes care of calculating cost of all ingredients ,
 *  and also provides descriptions of the sandwich.
 */
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
	
	/**
	 * This constructor creates a sandwich with the toppings. This object can be used as base sandwich on which toppings will be added.
	 * Initially the all the fields are set to base sandwich's attributes.
	 * @param baseSandwich
	 */
	public SandwichWithToppings(Sandwich baseSandwich) {
		this.baseSandwich = baseSandwich;
		this.name = baseSandwich.getName();
		this.cost = baseSandwich.getCost();
		this.description = baseSandwich.getDescription();
		this.surcharge = 0.0;
	}
	
	
	/**
	 *This method adds toppings to ingredient list, which will help to calculate the cost of sandwich 
	 *and also help to calculate the surcharges on extra toppings.
	 *
	 * @param toppingsToAdd
	 */
	public void addToppings(List<Ingredient> toppingsToAdd) {
		ingredientList = baseSandwich.getIngredientList();
		for(Ingredient newIngredient : toppingsToAdd){
			ingredientList.add(newIngredient);
		}
	}
	
	/**
	 * This methods calculates the surcharge of extra ingredients added to sandwich and will show up on the receipt.
	 * @return surcharge of extra ingredients
	 */
	public double getSurcharge(){
		if(ingredientList.size() > INGREDIENTS_BEFORE_SURCHARGE){
			int surchargedIngredients = ingredientList.size() - INGREDIENTS_BEFORE_SURCHARGE;
			this.surcharge = surchargedIngredients *SURCHARGE_PER_INGREDIENT;
		}
		return this.surcharge;
	}
	
	/**
	 * This method returns total cost which will help to calculate the total price of the sandwich on the receipt. It adds the cost 
	 * of all ingredients in the ingredient list.
	 * 
	 * @return cost of all ingredients.
	 */
	public double getCost(){
		double cost = 0;
		for(Ingredient ingredient : this.ingredientList){
			cost += ingredient.getPrice();
		}
		return cost;
	}
	
	/**
	 * This method returns list of ingredients it allows to get access to all ingredients used in making a sandwich.
	 * @return ingredientList 
	 */
	public List<Ingredient> getIngredientList(){
		return ingredientList;
	}
	
	/**
	 * This method returns name of the base sandwich to print it out on the receipt.
	 * 
	 * @return name of base sandwich.
	 */
	public String getName(){
		return this.baseSandwich.getName() + " with toppings.";
	}

	/**
	 * This method returns a descriptions about the base sandwich to print it out on the receipt.
	 * 
	 * @return description of the sandwich
	 */
	public String getDescription(){
		return this.baseSandwich.getDescription() + " with toppings.";
	}
	
	
	
	
}
