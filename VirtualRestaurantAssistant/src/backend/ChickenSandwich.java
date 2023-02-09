package backend;

import java.util.List;

public class ChickenSandwich extends Sandwich {

    public ChickenSandwich(List<Ingredient> ingredientList)
    {
        this.ingredientList = ingredientList;
        this.sandwichName = "Chicken";
        this.description = "Juicy Breasts and Thighs";
    }
}

