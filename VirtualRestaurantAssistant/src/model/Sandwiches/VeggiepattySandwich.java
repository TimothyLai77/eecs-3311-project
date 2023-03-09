package model.Sandwiches;

import java.util.List;

import model.Sandwich;
import model.Ingredients.Ingredient;

/**
 * Class representing a Veggiepatty Sandwich.
 * Extends the abstract class Sandwich.
 */

public class VeggiepattySandwich extends Sandwich {


    /**
     * Constructs a Veggiepatty Sandwich with a list of ingredients.
     *
     * @param ingredientList list of ingredients for the sandwich
     */

    public VeggiepattySandwich(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        this.sandwichName = "Veggiepatty";
        this.description = "Potatoes Galore";
    }

}