package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    Recipe recipe;
    Ingredients i1;
    Ingredients i2;
    Ingredients i3;
    Ingredients i4;
    Ingredients i5;

    ArrayList<Ingredients> ingredientList1;
    ArrayList<Ingredients> ingredientList2;

    @BeforeEach
    void runBefore() {
        recipe = new Recipe("Best Croissant", 9, 90);
        i1 = new Ingredients("flour", 1000, "g");
        i2 = new Ingredients("water", 300, "g");
        i3 = new Ingredients("sugar", 40, "g");
        i4 = new Ingredients("salt", 2, "g");
        i5 = new Ingredients("butter", 70, "g");
        ingredientList1 = new ArrayList<Ingredients>();
        ingredientList2 = new ArrayList<Ingredients>();
        ingredientList2.add(i1);
        ingredientList2.add(i2);
        ingredientList2.add(i3);
        ingredientList2.add(i4);
        ingredientList2.add(i5);
    }


    @Test
    void addIngredientTest() {
        ArrayList<Ingredients> i = new ArrayList<Ingredients>();
        i.add(i2);
        assertEquals(new ArrayList<Ingredients>(),ingredientList1);
        recipe.addIngredient(i2);
        assertEquals(i, recipe.getIngredientList());
    }

    @Test
    void findIngredientTest() {

        //return null
        assertEquals(null, recipe.findIngredient("ice cream"));
        //first in the list
        recipe.addIngredient(i1);
        recipe.addIngredient(i3);
        recipe.addIngredient(i5);
        assertEquals(i1, recipe.findIngredient("flour"));
        //last in the list
        assertEquals(i5, recipe.findIngredient("butter"));
        assertEquals(i5, recipe.findIngredient("Butter"));
        //in the middle of list
        assertEquals(i3, recipe.findIngredient("sugar"));
    }
    @Test
    void changeNameTest() {
        recipe.changeName("Best Baguette recipe");
        assertEquals("Best Baguette recipe", recipe.getRecipeName());
    }

    @Test
    void changePortionTest() {
        recipe.changePortion(4);
        assertEquals(4, recipe.getPortion());
    }

    @Test
     void changeTimeTest() {
        recipe.changeTime(100);
        assertEquals(100, recipe.getPrepTime());
    }
}
