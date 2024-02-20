package tasks.chunk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class ChunkTest {
    @DisplayName("Проверка функции chunk")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("provideChunk")
    public void chunkTest(String[] words, int size, String[][] expected) {
        Assertions.assertTrue(
                Arrays.deepEquals(Chunk.chunk(words, size), expected),
                Arrays.toString(words) + " с чанком " + size + " не равна " +
                        Arrays.deepToString(expected)
        );
    }

    private static Stream<Arguments> provideChunk() {
        String[] words = {"one", "two", "three", "four", "five", "six"};
        String[] empty = {};
        String[][] expectedEmpty = {};
        String[] one = {"one"};
        return Stream.of(
                Arguments.of(words, 2, new String[][]{{"one", "two"}, {"three", "four"}, {"five", "six"}}),
                Arguments.of(words, 3, new String[][]{{"one", "two", "three"}, {"four", "five", "six"}}),
                Arguments.of(words, 5, new String[][]{{"one", "two", "three", "four", "five"}, {"six"}}),
                Arguments.of(empty, 5, expectedEmpty),
                Arguments.of(one, 5, new String[][]{{"one"}})
        );
    }
}
