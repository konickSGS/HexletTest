package tasks.hotel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

public class AppTest {
    @DisplayName("Проверка Hotel")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideAppWithPredicate")
    public void testAppWithPredicate(Map<String, Object> expected, Map<String, Integer> predicates) {
        Map<String, Object> actual = App.findTheCheapestService(predicates);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideAppWithPredicate() {
        return Stream.of(
                Arguments.of(
                        Map.of(
                                "hotel", Map.of("cost", 616.0, "name", "mariiot"),
                                "service", "ostrovok"
                        ),
                        Map.of(
                                "min", 600,
                                "max", 800
                        )
                ),
                Arguments.of(
                        Map.of(
                                "hotel", Map.of("cost", 672.0, "name", "hexletInn"),
                                "service", "ostrovok"
                        ),
                        Map.of(
                                "min", 650,
                                "max", 700
                        )
                ),
                Arguments.of(
                        Map.of(
                                "hotel", Map.of("cost", 802.5, "name", "azimut"),
                                "service", "booking"
                        ),
                        Map.of(
                                "min", 800
                        )
                ),
                Arguments.of(
                        Map.of(
                                "hotel", Map.of("cost", 500.0, "name", "mariiot"),
                                "service", "kayak"
                        ),
                        Map.of(
                                "max", 570
                        )
                )
        );
    }
}
