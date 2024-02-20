package tasks.pascalsTriangle;

public class PascalsTriangle {
    public static int[] generate(int n) {
        if (n == 0)
            return new int[] {1};
        if (n == 1)
            return new int[] {1, 1};

        int[] previous = generate(n - 1);
        int[] current = new int[previous.length + 1];

        for (int i = 0; i < current.length; ++i) {
            current[i] = sumTwoPreviousValues(previous, i);
        }

        return current;
    }

    private static int sumTwoPreviousValues(int[] previous, int index) {
        if (index == 0 || index == previous.length)
            return 1;
        return previous[index - 1] + previous[index];
    }
}
