package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeConverterTest {

    RecipeConvertor rc;
    Recipe recipe;
    Ingredients i1;
    Ingredients i2;
    Ingredients i3;
    Ingredients i4;
    Ingredients i5;

    @BeforeEach
    void runBefore() {

        recipe = new Recipe("Best Croissant", 9, 90, "instructions");
        i1 = new Ingredients("flour", 1000, "g");
        i2 = new Ingredients("water", 300, "g");
        i3 = new Ingredients("sugar", 40, "g");
        i4 = new Ingredients("salt", 2, "g");
        i5 = new Ingredients("butter", 70, "g");

        recipe.addIngredient(i1);
        recipe.addIngredient(i2);
        recipe.addIngredient(i3);
        recipe.addIngredient(i4);
        recipe.addIngredient(i5);

        rc = new RecipeConvertor(recipe);
    }

    @Test
    void scaleBasedOnNumTest() {
        rc.scaleBasedOnNum(recipe,3);
        assertEquals(27, rc.scaledRecipe.getPortion());
        assertEquals(1000, recipe.findIngredient("flour").getAmount());
        assertEquals(3000, rc.scaledRecipe.findIngredient("flour").getAmount());
        assertEquals(900, rc.scaledRecipe.findIngredient("water").getAmount());
        assertEquals(120, rc.scaledRecipe.findIngredient("sugar").getAmount());
        assertEquals(6, rc.scaledRecipe.findIngredient("salt").getAmount());
        assertEquals(210, rc.scaledRecipe.findIngredient("butter").getAmount());
    }

    @Test
    void scaleBasedOnIngredientTest() {
        rc.scaleBasedOnIngredient(recipe, i5, 210);
        assertEquals(9, recipe.getPortion());
        assertEquals(27, rc.scaledRecipe.getPortion());
        assertEquals(3000, rc.scaledRecipe.findIngredient("flour").getAmount());
        assertEquals(900, rc.scaledRecipe.findIngredient("water").getAmount());
        assertEquals(120, rc.scaledRecipe.findIngredient("sugar").getAmount());
        assertEquals(6, rc.scaledRecipe.findIngredient("salt").getAmount());
        assertEquals(210, rc.scaledRecipe.findIngredient("butter").getAmount());
    }
}