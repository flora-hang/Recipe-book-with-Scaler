package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class RecipeBookTest {

    Recipe r1;
    Recipe r2;
    Recipe r3;
    Recipe r4;

    RecipeBook book;
    RecipeBook book2;

    @BeforeEach
    void runBefore() {
        r1 = new Recipe("New England Clam chowder", 4, 60);
        r2 = new Recipe("sauteé mushroom", 2, 30);
        r3 = new Recipe("Milk tea", 1, 20);
        r4 = new Recipe("Best ginger Bread", 6, 120);
        book = new RecipeBook("My collection");
        book2 = new RecipeBook("my book");
        book.addRecipe(r1);
        book.addRecipe(r2);
        book.addRecipe(r3);
    }
    @Test
    void addRecipeTest() {

        book2.addRecipe("Best ginger Bread", 6, 120);
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
