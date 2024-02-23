package tasks.enlargedImage;

import java.util.Arrays;
import java.util.stream.Stream;

public class EnlargedImage {
    public static String[][] enlargeArrayImage(String[][] matrix) {
        return enlargeArrayImage(matrix, 2);
    }

    public static String[][] enlargeArrayImage(String[][] arr, int coef) {

        return Arrays.stream(arr)
                .flatMap(line -> {
                    String[][] repeatElemets = new String[coef][];
                    Arrays.fill(repeatElemets, repeatElement(line, coef));
                    return Stream.of(repeatElemets);
                })
                .toArray(String[][]::new);
    }

    private static String[] repeatElement(String[] line, int coef) {
        return Arrays.stream(line)
                .flatMap(string -> {
                    String[] lines = new String[coef];
                    Arrays.fill(lines, string);
                    return Stream.of(lines);
                })
                .toArray(String[]::new);
    }
}
// END