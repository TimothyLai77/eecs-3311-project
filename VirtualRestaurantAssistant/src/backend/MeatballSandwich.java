package backend;

import java.util.List;

public class MeatballSandwich implements Sandwich {

    private List<Ingredient> ingredientList;
    private String sandwichName;
    private String description;

    public MeatballSandwich(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        this.sandwichName = "Meatball";
        this.description = "";
    }

    @Override
    public double getCost() {
        double cost = 0;
        for (Ingredient ingredient : ingredientList) {
            cost += ingredient.getPrice();
        }
        return cost;
    }

    @Override
    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    @Override
    public String getName() {
        return sandwichName;
    }

    @Override
    public String getDescription() {
        return description;
    }
}