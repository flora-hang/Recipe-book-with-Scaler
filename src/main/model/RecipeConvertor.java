package model;

import java.util.ArrayList;

public class RecipeConvertor {

    Recipe recipe;
    Recipe scaledRecipe;


    //EFFECTS: multiply the amount of ingredients by the number given,
    //and the portion of the recipe by the number.
    public RecipeConvertor(Recipe recipe) {
        this.recipe = recipe;
    }

    //REQUIRES: !recipe.getIngredientList().isEmpty()
    // EFFECT: scale the recipe given a number
    public Recipe scaleBasedOnNum(double scale) {


        scaledRecipe = new Recipe(recipe.getRecipeName() + ".scaledByNum",
                recipe.getPortion(), recipe.getPrepTime(), recipe.getInstruction());

        ArrayList<Ingredients> ingredients = recipe.getIngredientList();
        for (Ingredients i : ingredients) {
            scaledRecipe.addIngredient(i);
        }
        ArrayList<Ingredients> newIngredients = scaledRecipe.getIngredientList();
        scaledRecipe.changePortion(recipe.getPortion() * scale);

        for (Ingredients i : newIngredients) {
            double amount = i.getAmount();
            i.changeAmount(amount * scale);
        }

        return scaledRecipe;
    }

    //REQUIRES: the ingredient given to be in the same unit as the same ingredient in the recipe
    //EFFECT: scale based on the limiting ingredient, take the double of the amount of ingredient given
    //divided by the original amount, take that number, and multiply the ingredients and portion by it.
    public Recipe scaleBasedOnIngredient(Ingredients ingredient, double amount) {


        double baseAmount = recipe.findIngredient(ingredient.getName()).getAmount();
        double scaleConstant = amount / baseAmount;

        scaledRecipe = new Recipe(recipe.getRecipeName() + ".scaledByIngredient",
                recipe.getPortion(), recipe.getPrepTime(), recipe.getInstruction());

        ArrayList<Ingredients> ingredients = recipe.getIngredientList();
        for (Ingredients i : ingredients) {
            scaledRecipe.addIngredient(i);
        }
        scaledRecipe.changePortion(recipe.getPortion() * scaleConstant);

        recipe.changePortion(scaleConstant * recipe.getPortion());
        for (Ingredients i : scaledRecipe.getIngredientList()) {
            i.changeAmount(scaleConstant * i.getAmount());
        }
        return scaledRecipe;

    }
}
