package tasks.summaryRanges;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для задачи summaryRanges.
 */
public class SummaryRanges {

    public static void main(String[] args) {
        Condition<Integer> condition = (a, b) -> (b - a == 1);

        System.out.println(condition.make(2, 2));
        System.out.println(SummaryRanges.summaryRanges(new ArrayList<Integer>()));
        System.out.println(SummaryRanges.summaryRanges(List.of(1)));
        System.out.println(SummaryRanges.summaryRanges(List.of(1, 2, 3)));
    }

    /**
     * Метод должен найти в списке непрерывные возрастающие на единицу последовательности чисел и
     * вернуть список List<String> с их перечислением.
     * @param nums - list integer
     * @return List String в виду [1->3], [5->10]
     */
    public static List<String> summaryRanges(List<Integer> nums) {
        return getSummaryRangesByCondition(nums, (a, b) -> (b - a == 1));
    }

    /**
     * Улучшение метода summaryRanges для любых значений и условий.
     * @param list - лист любых значений
     * @param condition - Условие, которое должны выполнять соседние значения для непрерывной последовательности
     * @return List String в виду [1->3], [5->10]
     * @param <T> - любой
     */
    public static <T> List<String> getSummaryRangesByCondition(List<T> list, Condition<T> condition) {
        List<String> answer = new ArrayList<>();
        int startIndex = 0;

        for (int i = 1; i < list.size(); ++i) {
            if (!condition.make(list.get(i - 1), list.get(i))) {
                if (startIndex != i - 1) {
                    answer.add(list.get(startIndex) + "->" + list.get(i - 1));
                }
                startIndex = i;
            }
        }

        if (startIndex < list.size() - 1) {
            answer.add(list.get(startIndex) + "->" + list.get(list.size() - 1));
        }
        return answer;
    }
}
