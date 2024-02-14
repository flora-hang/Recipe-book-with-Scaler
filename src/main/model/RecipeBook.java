package model;

import java.util.ArrayList;


public class RecipeBook {
    ArrayList<Recipe> recipeBook;
    String nameOfRecipeBook;
    String collectionName;
    Recipe recipe;

    public RecipeBook(String name) {
        
        this.nameOfRecipeBook = name;
        recipeBook = new ArrayList<Recipe>();

    }

    //EFFECT: for test purpose, add a recipe to recipe book
    public void addRecipe(Recipe recipe) {
        recipeBook.add(recipe);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipeBook;
    }

    //EFFECT: based on inputting string find the recipe with matching name, ignore case
    public Recipe findRecipe(String name) {
        Recipe r = null;
        name = name.toLowerCase();
        for (Recipe recipe : recipeBook) {
            if (name.equals(recipe.getRecipeName().toLowerCase())) {
                r = recipe;
                return r;
            }
        }
        return r;
    }


}
