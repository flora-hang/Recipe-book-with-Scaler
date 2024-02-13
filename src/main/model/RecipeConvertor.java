package model;

import java.util.ArrayList;

public class RecipeConvertor {

    Recipe recipe;


    //EFFECTS: multiply the amount of ingredients by the number given,
    //and the portion of the recipe by the number.
    public RecipeConvertor(Recipe recipe) {
        this.recipe = recipe;
    }

    //EFFECT: scale the recipe given a number
    public Recipe scaleBasedOnNum(double scale) {
        ArrayList<Ingredients> ingredients = recipe.getIngredientList();

        recipe.changePortion(recipe.getPortion() * scale);

        for (Ingredients i : ingredients) {
            double amount = i.getAmount();
            i.changeAmount(amount * scale);
        }
        return recipe;
    }

    //REQUIRES: the ingredient given to be in the same unit as the same ingredient in the recipe
    //EFFECT: scale based on the limiting ingredient, take the double of the amount of ingredient given
    //divided by the original amount, take that number, and multiply the ingredients and portion by it.
    public Recipe scaleBasedOnIngredient(Ingredients ingredient, double amount) {

        double givenAmount = amount;
        double baseAmount = recipe.findIngredient(ingredient.getName()).getAmount();
        double scaleConstant = givenAmount / baseAmount;

        recipe.changePortion(scaleConstant * recipe.getPortion());
        for (Ingredients i : recipe.getIngredientList()) {
            i.changeAmount(scaleConstant * i.getAmount());
        }
        return recipe;

    }
}
