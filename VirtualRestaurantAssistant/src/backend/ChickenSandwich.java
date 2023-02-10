package backend;

import java.util.List;

/**
 * Class representing a Chicken Sandwich.
 * Extends the abstract class Sandwich.
 */

public class ChickenSandwich extends Sandwich {
	
	  /**
     * Constructs a Chicken Sandwich with a list of ingredients.
     *
     * @param ingredientList list of ingredients for the sandwich
     */

    public ChickenSandwich(List<Ingredient> ingredientList)
    {
        this.ingredientList = ingredientList;
        this.sandwichName = "Chicken";
        this.description = "Juicy Breasts and Thighs";
    }
}

