package backend;

import java.util.List;

public interface Sandwich {
	
	public double getCost();
	public List<Ingredient> getIngredientList();
	public String getName();
	public String getDescription();
}
