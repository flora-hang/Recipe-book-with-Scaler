package Json;

import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            RecipeBook recipeBook = new RecipeBook("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RecipeBook recipeBook = new RecipeBook("Collection");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(recipeBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            recipeBook = reader.read();
            assertEquals("Collection", recipeBook.getName());
            assertEquals(0, recipeBook.getRecipes().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            RecipeBook recipeBook = new RecipeBook("Collection");
            recipeBook.addRecipe(new Recipe("cake", 8, 20, "..."));
            recipeBook.addRecipe(new Recipe("tea", 6, 5, "..."));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(recipeBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            recipeBook = reader.read();
            assertEquals("Collection", recipeBook.getName());
            List<Recipe> recipes = recipeBook.getRecipes();
            assertEquals(2, recipes.size());
            checkRecipe("cake",  recipes.get(0), 8);
            checkRecipe("tea", recipes.get(1), 6);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
