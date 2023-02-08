package backend;

import java.util.List;

public class BeefSandwich implements Sandwich {

    private List<Ingredient> ingredientList;
    private String sandwichName;
    private String description;

    public BeefSandwich(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        this.sandwichName = "Beef";
        this.description = "";
    }

    @Override
    public String generateReceipt() {
        String receipt = "Receipt for " + sandwichName + "\n";
        receipt += "Ingredients:\n";
        for (Ingredient ingredient : ingredientList) {
            receipt += ingredient.getName() + " - " + ingredient.getPrice() + "\n";
        }
        receipt += "Total Cost: " + getCost();
        return receipt;
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
