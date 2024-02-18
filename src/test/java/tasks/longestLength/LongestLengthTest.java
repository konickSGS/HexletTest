package tasks.longestLength;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LongestLengthTest {
    @DisplayName("Проверка функции longestLength")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideLongestLength")
    public void testLongestLength(String str, int expectedValue) {
        int value = LongestLength.getLongestLength(str);
        Assertions.assertEquals(
                value,
                expectedValue,
                "Для строки " + str + " значение " + value + " не равно " + expectedValue
        );
    }

    private static Stream<Arguments> provideLongestLength() {
        return Stream.of(
                Arguments.of("jabjcdel", 7),
                Arguments.of("abcddef", 4),
                Arguments.of("abbccddeffg", 3),
                Arguments.of("abcd", 4),
                Arguments.of("1234561qweqwer", 9),
                Arguments.of("1234561qweqwerqer", 9),
                Arguments.of("", 0),
                Arguments.of("jabjcdeljrshmgdhj", 10)
        );
    }
}
