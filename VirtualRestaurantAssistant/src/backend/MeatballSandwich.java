package backend;

import java.util.List;

/**
 * Class representing a Meatball Sandwich.
 * Extends the abstract class Sandwich.
 */

public class MeatballSandwich extends Sandwich {

    /**
     * Constructs a Meatball Sandwich with a list of ingredients.
     *
     * @param ingredientList list of ingredients for the sandwich
     */

    public MeatballSandwich(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        this.sandwichName = "Meatball";
        this.description = "A sandwich of six meatballs.";
    }
}