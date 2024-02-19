package tasks.seaBattle;

public class SeaBattle {
    private static final int WATER = 0;
    /**
     * Метод, который возвращает количество кораблей на поле.
     * Принимает на вход поле боя в виде квадратного двумерного массива целых чисел из нулей и единиц.
     * Ноль — пустая ячейка, единица — часть корабля.
     *
     * @param battleField - массив, который содержит поле боя
     * @return количество кораблей
     */
    public static int calcShipsCount(int[][] battleField) {
        if (!isValidField(battleField))
            throw new IllegalArgumentException("Невалидное поле");
        //changeAllNotNullCells(battleField, -1);

        int count = 0;
        int[] sizes = getMatrixSize(battleField);
        int rowCount = sizes[0];
        int colCount = sizes[1];

        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                if (!isWater(battleField[i][j])) {
                    // Проверяем прошлые значения: сверху и слева.
                    // Так как мы проходи слева направо и сверху вниз, то мы проверяем именно их
                    // Если среди них есть часть корабля, то значит мы этот корабль уже учитывали
                    // Иначе увеличиваем счетчик на 1
                    if (isShipPart(battleField, i - 1, j) || isShipPart(battleField, i, j - 1)) {
                        // Рядом часть корабля, поэтому проходим мимо
                        continue;
                    } else {
                        // Частей корабля до этого нет, поэтому это новый корабль. Увеличиваем count
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Метод заменяет все ненулевые значения на replacement
     *
     * @param battleField - массив с полем боя
     * @param replacement - то, на что заменить значения поля
     */
    private static void changeAllNotNullCells(int[][] battleField, int replacement) {
        int[] sizes = getMatrixSize(battleField);
        int rowCount = sizes[0];
        int colCount = sizes[1];

        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                if (!isWater(battleField[i][j]))
                    battleField[i][j] = replacement;
            }
        }
    }

    /**
     * Метод проверяет расстановку кораблей на корректность.
     *
     * @param battleField - массив с полем боя
     * @return валидное ли поле
     */
    public static boolean isValidField(int[][] battleField) {
        int[] sizes = getMatrixSize(battleField);
        int rowCount = sizes[0];
        int colCount = sizes[1];

        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < colCount; ++j) {
                if (battleField[i][j] == 1 && !isValidCell(battleField, i, j))
                    return false;
            }
        }
        return true;
    }

    /**
     * Метод проверяет, что данная точка корабля валидна.
     * Только если 1 находятся на одной линии (вертикально или диагонально).
     * Если 1 есть на диагонали или единицы образуют "угол", то false
     *
     * @param battleField - массив с полем боя
     * @param ai          - индекс текущей строки
     * @param aj          - индекс текущего столбца
     * @return true, если хотя бы одна диагональ равна 1
     */
    private static boolean isValidCell(int[][] battleField, int ai, int aj) {
        if (!isValidDiagonals(battleField, ai, aj))
            return false;

        return isValidMainLines(battleField, ai, aj);
    }

    /**
     * Метод проверяет валидность главных линий. Если они образуют "угол", значит корьали соприкасаются.
     *
     * @param battleField - массив с полем боя
     * @param ai          - индекс текущей строки
     * @param aj          - индекс текущего столбца
     * @return true, если главные линии не содержат "угол". false - иначе.
     */
    private static boolean isValidMainLines(int[][] battleField, int ai, int aj) {
        boolean hasOneOnVerticalLine = isShipPart(battleField, ai + 1, aj) || isShipPart(battleField, ai - 1, aj);
        boolean hasOneOnHorizontalLine = isShipPart(battleField, ai, aj + 1) || isShipPart(battleField, ai, aj - 1);

        // Если единицы есть как на горизонтальной линии, так и на вертикальной, то значит есть "угол" - false
        // Если только на одной или ни на одной, то true
        if (hasOneOnHorizontalLine && hasOneOnVerticalLine)
            return false;
        return true;
    }

    /**
     * Метод проверяет, что вокруг нашей точки все диагонали равны 0.
     * Если хотя бы одна 1, это значит, что другой корабль соприкасается с нашей точкой
     *
     * @param battleField - массив с полем боя
     * @param ai          - индекс текущей строки
     * @param aj          - индекс текущего столбца
     * @return true, если хотя бы одна диагональ равна 1
     */
    private static boolean isValidDiagonals(int[][] battleField, int ai, int aj) {
        int[] rowIndexes = {ai - 1, ai + 1};
        int[] colIndexes = {aj - 1, aj + 1};
        for (int i : rowIndexes) {
            for (int j : colIndexes) {
                if (isShipPart(battleField, i, j))
                    return false;
            }
        }
        return true;
    }

    /**
     * Метод проверяет данную точку на часть корабля.
     * Если наша точка выходит за пределы поля, то false
     *
     * @param battleField - массив с полем боя
     * @param ai          - индекс текущей строки
     * @param aj          - индекс текущего столбца
     * @return true, если наша точка - часть корабля. false иначе (даже если точка не находится на поле)
     */
    private static boolean isShipPart(int[][] battleField, int ai, int aj) {
        int[] sizes = getMatrixSize(battleField);
        int rowCount = sizes[0];
        int colCount = sizes[1];

        if (ai < 0 || ai >= rowCount || aj < 0 || aj >= colCount)
            return false;
        return !isWater(battleField[ai][aj]);
    }

    /**
     * Метод возвращает размеры двумерного массива в виде массива [количество строк, количество столбцов].
     *
     * @param array - двумерный массив. Он должен быть квадратным
     * @return массив [количество строк, количество столбцов]
     */
    private static int[] getMatrixSize(int[][] array) {
        int[] size = new int[2];
        size[0] = array.length;
        size[1] = (size[0] > 0 ? array[0].length : 0);

        return size;
    }

    private static boolean isWater(int cellValue) {
        return cellValue == WATER;
    }
}
