package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    public IngredientTest() {

        Ingredients i1;
        Ingredients i2;
        Ingredients i3;
        Ingredients i4;
        Ingredients i5;

        @BeforeEach
        void runBefore() {
            i1 = new Ingredients("flour", 1000, "g");
            i2 = new Ingredients("water", 300, "g");
            i3 = new Ingredients("sugar", 40, "g");
            i4 = new Ingredients("salt", 2, "g");
            i5 = new Ingredients("butter", 70, "g");
        }

        @Test
        void UnitConvertorTest() {

        }

    }
}
