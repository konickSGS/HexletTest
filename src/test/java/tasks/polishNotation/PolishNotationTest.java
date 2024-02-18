package tasks.polishNotation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolishNotationTest {
    @Test
    public void testCalcInPolishNotation() {
        Assertions.assertEquals(PolishNotation.calcInPolishNotation("1 2 + 4 * 3 +"), 15);
        Assertions.assertEquals(PolishNotation.calcInPolishNotation("1 2 + 4 * 3 /"), 4);
        Assertions.assertEquals(PolishNotation.calcInPolishNotation("1 2 + 2 *"), 6);
        Assertions.assertEquals(PolishNotation.calcInPolishNotation("7 12 2 / -"), 1);
        Assertions.assertEquals(PolishNotation.calcInPolishNotation("7 2 3 * -"), 1);
        Assertions.assertEquals(PolishNotation.calcInPolishNotation("8 6 2 - /"), 2);
        Assertions.assertEquals(PolishNotation.calcInPolishNotation("5 2 - 0 +"), 3);
        //assertThatThrownBy(() -> App.calcInPolishNotation("3 1 + 0 /"))
        //        .isInstanceOf(ArithmeticException.class);
    }

    @Test
    public void testCalcInPolishNotationException() {
        // Ожидаем, что выражение вызовет ошибку типа
        Exception exception = Assertions.assertThrows(
                ArithmeticException.class,
                () -> PolishNotation.calcInPolishNotation("3 1 + 0 /")
        );
        //String expectedMessage = "For input string";
        //String actualMessage = exception.getMessage();
        //Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
