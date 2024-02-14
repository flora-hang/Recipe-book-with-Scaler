package model;

import java.util.ArrayList;

public class RecipeConvertor {

    Recipe original;
    Recipe scaledRecipe;


    Ingredients newI;

    //EFFECTS: multiply the amount of ingredients by the number given,
    //and the portion of the recipe by the number.
    public RecipeConvertor(Recipe original) {
        scaledRecipe = new Recipe(original.getRecipeName() + ".scaledByNum",
                original.getPortion(), original.getPrepTime(), original.getInstruction());

    }

    //REQUIRES: !recipe.getIngredientList().isEmpty()
    // EFFECT: scale the recipe given a number
    public Recipe scaleBasedOnNum(Recipe original, double scale) {


        scaledRecipe = new Recipe(original.getRecipeName() + ".scaledByNum",
                original.getPortion(), original.getPrepTime(), original.getInstruction());

        ArrayList<Ingredients> ingredients = original.getIngredientList();
        for (Ingredients i : ingredients) {
            newI = new Ingredients(i.getName(), i.getAmount(), i.getUnit());
            scaledRecipe.addIngredient(newI);
        }
        ArrayList<Ingredients> newIngredients = scaledRecipe.getIngredientList();
        scaledRecipe.changePortion(original.getPortion() * scale);

        for (Ingredients i : newIngredients) {
            i.changeAmount(i.getAmount() * scale);
        }

        return scaledRecipe;
    }

    //REQUIRES: the ingredient given to be in the same unit as the same ingredient in the recipe
    //EFFECT: scale based on the limiting ingredient, take the double of the amount of ingredient given
    //divided by the original amount, take that number, and multiply the ingredients and portion by it.
    public Recipe scaleBasedOnIngredient(Recipe original, Ingredients ingredient, double amount) {


        double baseAmount = ingredient.getAmount();
        double scaleConstant = amount / baseAmount;

        scaledRecipe = new Recipe(original.getRecipeName() + ".scaledByNum",
                original.getPortion(), original.getPrepTime(), original.getInstruction());

        ArrayList<Ingredients> ingredients = original.getIngredientList();
        for (Ingredients i : ingredients) {
            newI = new Ingredients(i.getName(), i.getAmount(), i.getUnit());
            scaledRecipe.addIngredient(newI);
        }
        ArrayList<Ingredients> newIngredients = scaledRecipe.getIngredientList();
        scaledRecipe.changePortion(original.getPortion() * scaleConstant);

        for (Ingredients i : scaledRecipe.getIngredientList()) {
            i.changeAmount(scaleConstant * i.getAmount());
        }
        return scaledRecipe;

    }
}
