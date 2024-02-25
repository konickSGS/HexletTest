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
                    String[][] repeatElements = new String[coef][];
                    Arrays.fill(repeatElements, repeat(line, coef));
                    return Stream.of(repeatElements);
                })
                .toArray(String[][]::new);
    }

    private static String[] repeat(String[] line, int coef) {
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