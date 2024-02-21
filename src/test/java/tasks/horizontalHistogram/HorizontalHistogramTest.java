package tasks.horizontalHistogram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class HorizontalHistogramTest {

    private static Supplier getRollGenerator(int seed) {
        Random rand = new Random(seed);
        Supplier generate = () -> (rand.nextInt(6) + 1);
        return generate;
    }

    @DisplayName("Проверка функции play")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("providePlay")
    public void testPlay(int count, String[] array) {
        String expected = String.join("\n", array);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Supplier rollDice = getRollGenerator(count);
        HorizontalHistogram.play(count, rollDice);

        String actual = out.toString().trim();
        System.out.println(actual);
        System.out.println(expected);

        Assertions.assertEquals(actual, expected, "Actual:\n" + actual + "\nExpected:\n" + expected);
    }

    private static Stream<Arguments> providePlay() {
        return Stream.of(
                Arguments.of(
                        125, new String[]{
                                "1|#################### 20",
                                "2|################### 19",
                                "3|###################### 22",
                                "4|######################### 25",
                                "5|##################### 21",
                                "6|################## 18"
                        }
                ),
                Arguments.of(
                        100, new String[]{
                                "1|################ 16",
                                "2|################# 17",
                                "3|################# 17",
                                "4|########## 10",
                                "5|###################### 22",
                                "6|################## 18"
                        }
                ), Arguments.of(
                        210, new String[]{
                                "1|############################################# 45",
                                "2|########################### 27",
                                "3|###################################### 38",
                                "4|######################### 25",
                                "5|######################## 24",
                                "6|################################################### 51"
                        }
                ), Arguments.of(
                        16, new String[]{
                                "1|",
                                "2|# 1",
                                "3|### 3",
                                "4|## 2",
                                "5|##### 5",
                                "6|##### 5"
                        }
                ), Arguments.of(
                        11, new String[]{
                                "1|# 1",
                                "2|",
                                "3|## 2",
                                "4|##### 5",
                                "5|# 1",
                                "6|## 2"
                        }
                ), Arguments.of(
                        7, new String[]{
                                "1|",
                                "2|",
                                "3|# 1",
                                "4|# 1",
                                "5|##### 5",
                                "6|"
                        }
                )
        );
    }
}
