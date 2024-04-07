package model;


import persistence.Writable;
import org.json.*;
import java.util.ArrayList;

public class RecipeBook implements Writable {
    ArrayList<Recipe> recipeBook;
    String nameOfRecipeBook;


    public RecipeBook(String name) {
        
        this.nameOfRecipeBook = name;
        recipeBook = new ArrayList<Recipe>();

    }

    //EFFECT: for test purpose, add a recipe to recipe book
    public void addRecipe(Recipe recipe) {
        recipeBook.add(recipe);
        EventLog.getInstance().logEvent(new Event("Added recipe: " + recipe.getRecipeName()));
    }

    public ArrayList<Recipe> getRecipes() {
        return recipeBook;
    }

    public String getName() {
        return nameOfRecipeBook;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", nameOfRecipeBook);
        json.put("recipes", recipesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray recipesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : recipeBook) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }



}
