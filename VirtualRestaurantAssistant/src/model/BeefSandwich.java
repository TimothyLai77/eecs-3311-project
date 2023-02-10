package model;

import java.util.List;

public class BeefSandwich extends Sandwich {


    public BeefSandwich(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        this.sandwichName = "Beef";
        this.description = "A5 wagyu Beef Sandiwch";
    }

}