package tasks.summaryRanges;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SummaryRangesTest {
    @DisplayName("Проверка функции summaryRanges")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideSummaryRanges")
    public void testSummaryRanges(List<Integer> nums, List<String> expected) {
        Assertions.assertEquals(
                SummaryRanges.summaryRanges(nums),
                expected
        );
    }

    private static Stream<Arguments> provideSummaryRanges() {
        return Stream.of(
                Arguments.of(
                        new ArrayList<Integer>(),
                        new ArrayList<String>()
                ),
                Arguments.of(
                        List.of(1),
                        new ArrayList<String>()
                ),
                Arguments.of(
                        List.of(1, 2, 3),
                        List.of("1->3")
                ),
                Arguments.of(
                        List.of(8, 3, 1, 12, 2, 5),
                        new ArrayList<String>()
                ),
                Arguments.of(
                        List.of(8, 3, 1, 2, 3),
                        List.of("1->3")
                ),
                Arguments.of(
                        List.of(0, 1, 2, 4, 5, 7),
                        List.of("0->2", "4->5")
                ),
                Arguments.of(
                        List.of(1, 1, 3, 4, 5, -6, 8, 9, 10, 12, 14, 14),
                        List.of("3->5", "8->10")
                ),
                Arguments.of(
                        List.of(110, 111, 112, 111, -5, -4, -2, -3, -4, -5),
                        List.of("110->112", "-5->-4")
                )
        );
    }
}
