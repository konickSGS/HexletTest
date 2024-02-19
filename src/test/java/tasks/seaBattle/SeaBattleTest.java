package tasks.seaBattle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static tasks.seaBattle.SeaBattle.calcShipsCount;
import static tasks.seaBattle.SeaBattle.isValidField;

public class SeaBattleTest {
    @DisplayName("Проверка поля на количество кораблей")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideCalcShipsCount")
    public void testCalcShipsCount(int[][] battleField, int expectedCount) {
        Assertions.assertEquals(calcShipsCount(battleField), expectedCount);
    }

    @DisplayName("Проверка поля на валидность")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideValidateFieldNegative")
    public void testValidateField(int[][] battleField, boolean expected) {
        Assertions.assertEquals(isValidField(battleField), expected);
    }

    private static Stream<Arguments> provideCalcShipsCount() {
        return Stream.of(
                Arguments.of(
                        new int[][]{},
                        0
                ),
                Arguments.of(
                        new int[][]{{1}},
                        1
                ),
                Arguments.of(
                        new int[][]{{0}},
                        0
                ),
                Arguments.of(
                        new int[][]{
                                {0, 0, 1},
                                {0, 0, 0},
                                {1, 1, 0},
                        }, 2
                ),
                Arguments.of(
                        new int[][]{
                                {1, 1, 0, 0, 0, 0},
                                {0, 0, 0, 1, 1, 1},
                                {0, 0, 0, 0, 0, 0},
                                {0, 1, 1, 1, 0, 1},
                                {0, 0, 0, 0, 0, 1},
                                {1, 1, 0, 1, 0, 0},
                        }, 6
                )
        );
    }

    private static Stream<Arguments> provideValidateFieldNegative() {
        return Stream.of(
                Arguments.of(
                        new int[][]{}, true
                ),
                Arguments.of(
                        new int[][]{
                                {0, 1, 1, 0},
                                {0, 0, 0, 0},
                                {0, 1, 0, 0},
                                {0, 1, 0, 1},
                        }, true
                ),
                Arguments.of(
                        new int[][]{
                                {1, 1, 0, 0, 0, 0},
                                {0, 0, 0, 1, 1, 1},
                                {0, 0, 0, 0, 0, 0},
                                {0, 1, 1, 1, 0, 1},
                                {0, 0, 0, 0, 0, 1},
                                {1, 1, 0, 1, 0, 0},
                        }, true
                ),
                Arguments.of(
                        new int[][]{
                                {1, 1, 0, 0, 0, 0},
                                {0, 0, 0, 1, 1, 1},
                                {0, 0, 0, 0, 0, 0},
                                {0, 1, 1, 0, 0, 1},
                                {0, 0, 1, 0, 0, 1},
                                {1, 0, 0, 1, 0, 0},
                        }, false
                ),
                Arguments.of(
                        new int[][]{
                                {0, 1, 0, 0},
                                {1, 0, 0, 1},
                                {0, 0, 0, 0},
                                {0, 1, 1, 1},
                        }, false
                ),
                Arguments.of(
                        new int[][]{
                                {0, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 1, 0},
                                {0, 0, 0, 1},
                        }, false
                )
        );
    }
}

