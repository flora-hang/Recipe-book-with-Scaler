package Json;

import model.Ingredients;
import model.Recipe;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonTest {
    protected void checkRecipe(String name, Recipe recipe, int portion) {
        assertEquals(name, recipe.getRecipeName());
        assertEquals(portion, recipe.getPortion());
    }
}

