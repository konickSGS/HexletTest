package tasks.polishNotation;

import java.util.Deque;
import java.util.LinkedList;
public class PolishNotation {
    public static int calcInPolishNotation(String polishNotation) {
        Deque<Integer> stack = new LinkedList<>();

        for (String value: polishNotation.split(" ")) {
            if (Operand.contains(value)) {
                if (stack.size() < 2) {
                    throw new RuntimeException("Количество значений в стеке меньше 2. Неверная польская нотация");
                }
                Integer b = stack.pollFirst();
                Integer a = stack.pollFirst();

                Operand operand = Operand.valueOfSign(value);
                stack.addFirst(operand.calculate(a, b));
            } else {
                stack.addFirst(Integer.parseInt(value));
            }
        }
        if (stack.size() != 1) {
            throw new RuntimeException("В стеке осталось больше одного значения");
        }
        return stack.poll();
    }

    public static void main(String[] args) {

        System.out.println(Operand.DEL.calculate(12, 4));
    }
}
