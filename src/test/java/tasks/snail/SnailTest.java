package tasks.snail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SnailTest {
    @DisplayName("Проверка функции buildSnailPath")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideSnail")
    public void buildSnailPathTest(int[][] matrix, int[] expected) {
        Assertions.assertArrayEquals(Snail.buildSnailPath(matrix), expected);
    }

    private static Stream<Arguments> provideSnail() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {1, 2},
                                {3, 4}
                        }, new int[]{1, 2, 4, 3}
                ),
                Arguments.of(
                        new int[][]{
                                {1, 2, 3, 4},
                                {5, 6, 7, 8},
                                {9, 10, 11, 12}
                        }, new int[]{1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7}
                ),
                Arguments.of(
                        new int[][]{},
                        new int[]{}
                ),
                Arguments.of(
                        new int[][]{
                                {1, 2, 3, 4}
                        }, new int[]{1, 2, 3, 4}
                ),
                Arguments.of(
                        new int[][]{
                                {1},
                                {2},
                                {3},
                                {4}
                        }, new int[]{1, 2, 3, 4}
                ),
                Arguments.of(
                        new int[][]{
                                {1, 2, 3, 4, 5, 6, 7},
                                {8, 9, 10, 11, 12, 13, 14},
                                {15, 16, 17, 18, 19, 20, 21},
                                {22, 23, 24, 25, 26, 27, 28},
                                {29, 30, 31, 32, 33, 34, 35},
                                {36, 37, 38, 39, 40, 41, 42},
                                {43, 44, 45, 46, 47, 48, 49}
                        }, new int[]
                                {
                                        1, 2, 3, 4, 5, 6, 7,
                                        14, 21, 28, 35, 42, 49,
                                        48, 47, 46, 45, 44, 43,
                                        36, 29, 22, 15, 8,
                                        9, 10, 11, 12, 13,
                                        20, 27, 34, 41,
                                        40, 39, 38, 37,
                                        30, 23, 16,
                                        17, 18, 19,
                                        26, 33,
                                        32, 31,
                                        24,
                                        25
                                }
                )
        );
    }
}

