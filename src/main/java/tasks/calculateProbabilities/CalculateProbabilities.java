package tasks.calculateProbabilities;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculateProbabilities {
    public static Map<Integer, Map<Integer, Double>> calculateProbabilities(List<Integer> diceRolls) {
        var groupingIndexes = getNextIndexesValues(diceRolls);
        return groupingIndexes
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> getFrequency(e.getValue())
                ));
    }

    /**
     * Метод возвращает словарь, где key - элемент, а value - list элементов, которые следуют за ними.
     *
     * @param list - лист
     * @return мапа
     */
    private static Map<Integer, List<Integer>> getNextIndexesValues(List<Integer> list) {
        var groupingIndexes = getIndexes(list);

        return groupingIndexes.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue()
                                .stream()
                                .filter(i -> i + 1 != list.size())
                                .map(i -> list.get(i + 1))
                                .collect(Collectors.toList())
                ));
    }

    /**
     * Метод возвращает словарь, где key - элемент листа.
     * А value - list индексов вхождений этих элементов.
     *
     * @param list - лист
     * @return мапа
     */
    private static Map<Integer, List<Integer>> getIndexes(List<Integer> list) {
        return IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.groupingBy(i -> list.get(i)));
    }

    /**
     * Метод возвращает частоту элементов.
     * Для каждого элемента вычисляются количество вхождений этого элемента.
     * Потом число вхождений этого элемента делятся на длину элементов в листе.
     *
     * @param list - лист
     * @return - мапа
     */
    private static Map<Integer, Double> getFrequency(List<Integer> list) {
        var count = list.stream()
                .collect(Collectors.groupingBy(
                        i -> i,
                        Collectors.counting()
                ));
        double size = list.size();
        return count
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue() / size
                ));
    }
}
