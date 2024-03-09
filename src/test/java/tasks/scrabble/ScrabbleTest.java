package tasks.scrabble;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ScrabbleTest {
    @DisplayName("Проверка функции scrabbleTest")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideScrabble")
    public void scrabbleTest(CharSequence letters, CharSequence str, Boolean expected) {
        Boolean actual = Scrabble.scrabble(letters, str);
        Assertions.assertEquals(
                actual,
                expected,
                String.format(
                        "Из символов %s %s сложить %s. Но результат неверный.",
                        letters,
                        expected ? "можно" : "нельзя",
                        str
                )
        )
        ;
    }
    private static Stream<Arguments> provideScrabble() {
        return Stream.of(
                Arguments.of("rkqodlw", "world", true),
                Arguments.of("begsdhhtsexoult", "hexlet", true),
                Arguments.of("nastenka", "steak", true),
                Arguments.of("scriptjava", "javascript", true),
                Arguments.of("scriptingjava", "JavaScript", true),
                Arguments.of("katas", "steak", false),
                Arguments.of("javasprint", "javascript", false),
                Arguments.of("scriptjavest", "javascript", false),
                Arguments.of("", "javascript", false)
        );
    }
}
