package tasks.snail;

public class Snail {
    public static void main(String[] args) {
        int[][] matrix = new int[7][7];
        int n = 7;
        for (int i = 1; i <= n; ++i) {
            System.out.print("{");
            System.out.print(1 + (i - 1) * n);
            for (int j = 2; j <= n; j++) {
                System.out.print(", " + (j + (i - 1) * n));
            }
            System.out.println("},");
        }
    }
    public static int[] buildSnailPath(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = (rowCount > 0 ? matrix[0].length : 0);

        int cellCount = rowCount * colCount;
        int[] snailPath = new int[cellCount];

        int rightBarrier = colCount - 1;
        int leftBarrier = 0;
        int bottomBarrier = rowCount - 1;
        int topBarrier = 1;

        boolean isGoRight = true;
        boolean isGoBottom = true;

        int i = 0;
        int j = 0;

        for (int snailIndex = 0; snailIndex < cellCount; ++snailIndex) {
            snailPath[snailIndex] = matrix[i][j];

            if (isGoRight && isGoBottom) {
                if (j == rightBarrier) {
                    isGoRight = false;
                    rightBarrier--;
                    i++;
                } else {
                    j++;
                }
            } else if (!isGoRight && isGoBottom) {
                if (i == bottomBarrier) {
                    isGoBottom = false;
                    bottomBarrier--;
                    j--;
                } else {
                    i++;
                }
            } else if (!isGoRight && !isGoBottom) {
                if (j == leftBarrier) {
                    isGoRight = true;
                    leftBarrier++;
                    i--;
                } else {
                    j--;
                }
            } else if (isGoRight && !isGoBottom) {
                if (i == topBarrier) {
                    isGoBottom = true;
                    topBarrier++;
                    j++;
                } else {
                    i--;
                }
            }
        }

        return snailPath;
    }
}
