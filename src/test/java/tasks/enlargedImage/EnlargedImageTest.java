package tasks.enlargedImage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class EnlargedImageTest {
    @DisplayName("Проверка функции enlargeArrayImage")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideEnlargeArrayImage")
    public void testEnlargeArrayImage(String[][] matrix, String[][] expected) {
        String[][] actual = EnlargedImage.enlargeArrayImage(matrix);

        Assertions.assertTrue(Arrays.deepEquals(actual, expected), Arrays.deepToString(actual) + "\n" + Arrays.deepToString(expected));
    }

    private static Stream<Arguments> provideEnlargeArrayImage() {
        return Stream.of(
                Arguments.of(
                        new String[][]{
                                {"*"}
                        },
                        new String[][]{
                                {"*", "*"},
                                {"*", "*"}}
                ),
                Arguments.of(
                        new String[][]{
                                {"*", "*", "*", "*"},
                                {"*", " ", " ", "*"},
                                {"*", " ", " ", "*"},
                                {"*", "*", "*", "*"}},
                        new String[][]{
                                {"*", "*", "*", "*", "*", "*", "*", "*"},
                                {"*", "*", "*", "*", "*", "*", "*", "*"},
                                {"*", "*", " ", " ", " ", " ", "*", "*"},
                                {"*", "*", " ", " ", " ", " ", "*", "*"},
                                {"*", "*", " ", " ", " ", " ", "*", "*"},
                                {"*", "*", " ", " ", " ", " ", "*", "*"},
                                {"*", "*", "*", "*", "*", "*", "*", "*"},
                                {"*", "*", "*", "*", "*", "*", "*", "*"}
                        }
                )
        );
    }
}
// END