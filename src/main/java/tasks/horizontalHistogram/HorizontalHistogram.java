package tasks.horizontalHistogram;

import java.io.PrintStream;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.function.Supplier;

public class HorizontalHistogram {
    public static void play(int diceRollsNumber, Supplier<Integer> diceSupplier) {
        int[] dice = {0, 0, 0, 0, 0, 0};
        for (int i = 0; i < diceRollsNumber; ++i) {
            dice[diceSupplier.get() - 1]++;
        }

        printHorizontalHistogram(dice, System.out);
    }

    public static void printHorizontalHistogram(int[] array, PrintStream out) {
        for (int i = 0; i < array.length; ++i) {
            out.print(i + 1 + "|");
            out.print("#".repeat(array[i]));
            if (array[i] != 0)
                out.print(" " + array[i]);
            //if (i != array.length - 1)
            out.print("\n");
        }
    }
}
