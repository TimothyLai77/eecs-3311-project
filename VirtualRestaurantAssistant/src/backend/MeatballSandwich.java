package backend;

import java.util.List;

public class MeatballSandwich extends Sandwich {

    public MeatballSandwich(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        this.sandwichName = "Meatball";
        this.description = "A sandwich of six meatballs.";
    }




}