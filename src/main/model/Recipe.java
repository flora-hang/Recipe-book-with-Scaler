package model;

public class Recipe {

    private String recipeName;
    private boolean isVegan;
    private boolean isKosher;
    private int portion;
    private int prepTime;

    public Recipe(String recipeName, int portion, int prepTime, boolean isKosher, boolean isVegan) {

        this.isKosher = isKosher;
        this.isVegan = isVegan;
        this.portion = portion;
        this.recipeName = recipeName;
        this.prepTime = prepTime;

    }

}
