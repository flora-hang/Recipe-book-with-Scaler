package Json;

import model.Ingredients;
import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    void testWriterEmptyRecipeBook() {
        try {
            RecipeBook recipeBook = new RecipeBook("Collection");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecipeBook.json");
            writer.open();
            writer.write(recipeBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRecipeBook.json");
            recipeBook = reader.read();
            assertEquals("Collection", recipeBook.getName());
            assertEquals(0, recipeBook.getRecipes().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRecipeBook() {
        try {
            RecipeBook recipeBook = new RecipeBook("Collection");
            Recipe recipe1 = new Recipe("cake", 8, 20, "...");
            Recipe recipe2 = new Recipe("tea", 6, 5, "...");
            Ingredients i1 = new Ingredients("egg", 3, "");
            Ingredients i2 = new Ingredients("tea leaf", 3, "g");
            recipe1.addIngredient(i1);
            recipe2.addIngredient(i2);
            recipeBook.addRecipe(recipe1);
            recipeBook.addRecipe(recipe2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRecipeBook.json");
            writer.open();
            writer.write(recipeBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRecipeBook.json");
            recipeBook = reader.read();
            assertEquals("Collection", recipeBook.getName());
            List<Recipe> recipes = recipeBook.getRecipes();
            List<Ingredients> ingredients1 = recipe1.getIngredientList();
            List<Ingredients> ingredients2 = recipe2.getIngredientList();
            assertEquals(2, recipes.size());
            assertEquals(ingredients2, recipe2.getIngredientList());
            assertEquals(ingredients1, recipe1.getIngredientList());
            checkRecipe("cake",  recipes.get(0), 8);
            checkRecipe("tea", recipes.get(1), 6);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
