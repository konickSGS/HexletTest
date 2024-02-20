package tasks.pascalsTriangle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class PascalsTriangleTest {
    @DisplayName("Проверка функции generate. Треугольник Паскаля")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideGenerate")
    public void testGenerate(int[] expected, int num) {
        int[] actual = PascalsTriangle.generate(num);
        Assertions.assertArrayEquals(actual, expected, "Результат " + Arrays.toString(actual) + " не равен " + Arrays.toString(expected));
    }


    private static Stream<Arguments> provideGenerate() {
        return Stream.of(
                Arguments.of(new int[]{1}, 0),
                Arguments.of(new int[]{1, 1}, 1),
                Arguments.of(new int[]{1, 2, 1}, 2),
                Arguments.of(new int[]{1, 3, 3, 1}, 3),
                Arguments.of(new int[]{1, 7, 21, 35, 35, 21, 7, 1}, 7)
        );
    }
}
