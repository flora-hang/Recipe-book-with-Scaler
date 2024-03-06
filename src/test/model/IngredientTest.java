package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    Ingredients i1;
    Ingredients i2;
    Ingredients i3;
    Ingredients i4;
    Ingredients i5;

    ArrayList<Ingredients> ingredientList1;
    ArrayList<Ingredients> ingredientList2;

    @BeforeEach
    void runBefore() {
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
    void changeNameTest() {
        i1.changeName("wheat flour");
        assertEquals("wheat flour", i1.getName());
    }

    @Test
    void changeAmountTest() {
        i2.changeAmount(600);
        assertEquals(600, i2.getAmount());
    }

    @Test
    void changeUnitTest() {
        i3.changeUnit("ml");
        assertEquals("ml", i3.getUnit());
    }
}
