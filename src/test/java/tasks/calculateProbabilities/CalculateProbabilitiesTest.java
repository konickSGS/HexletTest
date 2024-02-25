package tasks.calculateProbabilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CalculateProbabilitiesTest {
    @DisplayName("Проверка функции enlargeArrayImage")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideCalculateProbabilities")
    public void testCalculateProbabilities(List<Integer> diceRolls, Map<Integer, Map<Integer, Double>> expected) {
        var actual = CalculateProbabilities.calculateProbabilities(diceRolls);
        Assertions.assertEquals(
                actual,
                expected
        );
    }

    private static Stream<Arguments> provideCalculateProbabilities() {
        return Stream.of(
                Arguments.of(
                        new ArrayList<Integer>(),
                        new HashMap<Integer, Map<Integer, Double>>()
                ),
                Arguments.of(
                        List.of(1, 3, 1, 5, 1),
                        Map.of(
                                1, Map.of(3, 0.5, 5, 0.5),
                                3, Map.of(1, 1.0),
                                5, Map.of(1, 1.0)
                        )
                ),
                Arguments.of(
                        List.of(1, 3, 1, 5, 1, 2, 1, 6),
                        Map.of(
                                1, Map.of(2, 0.25, 3, 0.25, 5, 0.25, 6, 0.25),
                                2, Map.of(1, 1.0),
                                3, Map.of(1, 1.0),
                                5, Map.of(1, 1.0),
                                6, Map.of()
                        )
                ),
                Arguments.of(
                        List.of(2, 3, 2, 5, 2, 2, 1, 3),
                        Map.of(
                                1, Map.of(3, 1.0),
                                2, Map.of(1, 0.25, 2, 0.25, 3, 0.25, 5, 0.25),
                                3, Map.of(2, 1.0),
                                5, Map.of(2, 1.0)
                        )
                ),
                Arguments.of(
                        List.of(1, 3, 1, 5, 1, 2, 1, 6, 1, 3),
                        Map.of(
                                1, Map.of(2, 0.2, 3, 0.4, 5, 0.2, 6, 0.2),
                                2, Map.of(1, 1.0),
                                3, Map.of(1, 1.0),
                                5, Map.of(1, 1.0),
                                6, Map.of(1, 1.0)
                        )
                )
        );
    }
}
