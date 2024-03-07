package Json;

import model.Recipe;
import model.Ingredients;
import model.RecipeBook;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RecipeBook recipeBook = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecipeBook.json");
        try {
            RecipeBook recipeBook = reader.read();
            assertEquals("Collection", recipeBook.getName());
            assertEquals(0, recipeBook.getRecipes().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRecipeBook.json");
        try {
            RecipeBook recipeBook = reader.read();
            assertEquals("Collection", recipeBook.getName());
            List<Recipe> recipes = recipeBook.getRecipes();
            assertEquals(2, recipes.size());
            checkRecipe("cake", recipes.get(0), 8);
            checkRecipe("tea", recipes.get(1), 6);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}