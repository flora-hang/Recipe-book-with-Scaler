package model;


import java.lang.reflect.Array;
import java.util.ArrayList;

//representing a recipe with a name, how many portions is served,
// the prep time, if it is vegan, if it is kosher

public class Recipe {

    private String recipeName;
    private double portion;
    private int prepTime;
    private ArrayList<Ingredients> ingredientList;
    private String instruction;

    //EFFECT: constructs a recipe with a name, how many portions is served,
    // the prep time, if it is vegan, if it is kosher
    public Recipe(String recipeName, int portion, int prepTime) {

        this.portion = portion;
        this.recipeName = recipeName;
        this.prepTime = prepTime;
        ingredientList = new ArrayList<Ingredients>();

    }

    //EFFECT: add instruction to recipe
    public void addInstruction(String text) {
        instruction = text;
    }

    public String getInstruction() {
        return instruction;
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

    //REQUIRES: no replication of ingredient name in list
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

}
