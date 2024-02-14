package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeBookTest {

    Recipe r1;
    Recipe r2;
    Recipe r3;
    Recipe r4;

    ArrayList<Ingredients> l1;
    RecipeBook book;
    RecipeBook book2;

    @BeforeEach
    void runBefore() {
        l1 = new ArrayList<Ingredients>();

        r1 = new Recipe("New England Clam chowder", 4, 60, "instructions");
        r2 = new Recipe("mushroom", 2, 30, "instructions");
        r3 = new Recipe("Milk tea", 1, 20, "instruction");
        r4 = new Recipe("Best ginger Bread", 6, 120, "instruction");
        book = new RecipeBook("My collection");
        book2 = new RecipeBook("my book");
        book.addRecipe(r1);
        book.addRecipe(r2);
        book.addRecipe(r3);

    }
    @Test
    void addRecipeTest() {

        book2.addRecipe(r4);
        assertEquals(book2.getRecipes().get(0), book2.findRecipe("Best ginger Bread"));
    }

    @Test
    void findRecipeTest() {

        assertNull(book.findRecipe("hot chocolate"));
        assertEquals(r3, book.findRecipe("Milk Tea"));
        assertEquals(r3, book.findRecipe("milk tea"));
        assertEquals(r1, book.findRecipe("New England Clam chowder"));
        assertEquals(r1, book.findRecipe("new england clam chowder"));

    }

}
