package tasks.knapsack_problem;

import java.util.List;
import java.util.Map;

public class KnapsackProblem {
    public static void main(String[] args) {
        List<Map<String, Object>> items = List.of(
                Map.of("name", "backpack", "weight", 6, "cost", 30),
                Map.of("name", "headphones", "weight", 1, "cost", 20),
                Map.of("name", "book", "weight", 4, "cost", 20),
                Map.of("name", "phone", "weight", 3, "cost", 15)
        );

        System.out.println(knapsackProblemSolution(items, 9));

    }

    public static int knapsackProblemSolution(List<Map<String, Object>> items, int maxCapacity) {
        int matrix[][] = new int[items.size() + 1][maxCapacity + 1];

        for (int itemIndex = 1; itemIndex <= items.size(); itemIndex++) {
            for (int currentCapacity = 1; currentCapacity <= maxCapacity; ++currentCapacity) {
                int itemWeight = (int) items.get(itemIndex - 1).get("weight");

                if (itemWeight > currentCapacity) {
                    matrix[itemIndex][currentCapacity] = matrix[itemIndex - 1][currentCapacity];
                } else {
                    int costWithoutCurrentItem = matrix[itemIndex - 1][currentCapacity];

                    int itemCost = (int) items.get(itemIndex - 1).get("cost");
                    int costWithoutCurrentWeight = matrix[itemIndex - 1][currentCapacity - itemWeight];
                    int costWithCurrentItem = itemCost + costWithoutCurrentWeight;

                    matrix[itemIndex][currentCapacity] = Math.max(costWithoutCurrentItem, costWithCurrentItem);
                }
            }
        }

        return matrix[items.size()][maxCapacity];
    }
}
