package backend;

import java.util.List;

public interface Sandwich {
	public String generateReceipt();
	public double getCost();
	public List<Ingredient> getIngredientList();
	public String getName();
	public String getDescription();
}
