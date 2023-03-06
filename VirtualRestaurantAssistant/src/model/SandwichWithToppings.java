package model;

import java.util.List;

public class SandwichWithToppings extends Sandwich {
	
	private Sandwich baseSandwich;
	private String name;
	private double cost;
	private String description;
	
	public SandwichWithToppings(Sandwich baseSandwich) {
		this.baseSandwich = baseSandwich;
		this.name = baseSandwich.getName();
		this.cost = baseSandwich.getCost();
		this.description = baseSandwich.getDescription();
	}
	
	
	public void addToppings(List<String> toppingsToAdd) {
		
	}
	
	
	
	
}
