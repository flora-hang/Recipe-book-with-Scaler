package model;


import org.json.JSONObject;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


//representing a recipe with a name, how many portions is served, the prep time

public class Recipe implements Writable {

    private String recipeName;
    private double portion;
    private int prepTime;
    private String instructions;
    private ArrayList<Ingredients> ingredientList;


    //EFFECT: constructs a recipe with a name, how many portions is served,
    // the prep time
    public Recipe(String recipeName, double portion, int prepTime,
                  String instruction) {

        this.portion = portion;
        this.recipeName = recipeName;
        this.prepTime = prepTime;
        instructions = instruction;
        ingredientList = new ArrayList<>();

    }

    public String getInstruction() {
        return instructions;
    }

    //REQUIRES: ingredient has a different name than ingredients in the list
    //MODIFIES: this
    //EFFECT: add a ingredient to the list of ingredients
    public void addIngredient(Ingredients ingredient) {
        ingredientList.add(ingredient);
    }

    public double getPortion() {
        return portion;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public String getRecipeName() {
        return this.recipeName;
    }

    public void changeName(String newName) {
        this.recipeName = newName;
    }

    public void changePortion(double newPortion) {
        this.portion = newPortion;
    }

    public void changeTime(int newTime) {
        this.prepTime = newTime;
    }

    //EFFECT: find ingredient based on inputted string, ignore case
    public Ingredients findIngredient(String name) {
        Ingredients ing = null;
        name = name.toLowerCase();
        for (Ingredients ingredient : ingredientList) {

            if (name.equals(ingredient.name.toLowerCase())) {
                ing = ingredient;
                return ing;
            }
        }

        return ing;
    }

    public ArrayList<Ingredients> getIngredientList() {

        return ingredientList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Recipe name", recipeName);
        json.put("Recipe portion", portion);
        json.put("Recipe prep time", prepTime);
        json.put("Recipe instruction", instructions);
        json.put("ingredients", ingredientsToJSon());
        return json;
    }

    private JSONArray ingredientsToJSon() {
        JSONArray jsonArray = new JSONArray();

        for (Ingredients i : ingredientList) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

}
