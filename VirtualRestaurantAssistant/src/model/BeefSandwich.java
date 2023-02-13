package model;

import java.util.List;

/**
 * Class representing a Beef Sandwich.
 * Extends the abstract class Sandwich.
 */

public class BeefSandwich extends Sandwich {


    /**
     * Constructs a Beef Sandwich with a list of ingredients.
     *
     * @param ingredientList list of ingredients for the sandwich
     */

    public BeefSandwich(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        this.sandwichName = "Beef";
        this.description = "A5 wagyu Beef Sandiwch";
    }

}