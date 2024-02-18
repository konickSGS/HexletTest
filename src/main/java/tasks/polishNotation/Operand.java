package tasks.polishNotation;

import java.util.HashMap;
import java.util.Map;

/**
 * Перечисление для знаков.
 * У каждого знака есть sign(+, -. *, /) и функция, которая соответствует этому знаку соответствует.
 */
public enum Operand {
    PLUS("+", (a, b) -> a + b),
    MINUS("-", (a, b) -> a - b),
    MUL("*", (a, b) -> a * b),
    DEL("/", (a, b) -> a / b);
    /**
     * Знак - строковое представление операнда.
     */
    private final String sign;
    /**
     * Функция для знака.
     */
    private final Calculate<Integer, Integer> func;

    /**
     * Мапа, которая делает соответствие между строковым знаком и самими объектом
     */
    private static final Map<String, Operand> BY_SIGN = new HashMap<>();

    static {
        for (Operand operand : values()) {
            BY_SIGN.put(operand.sign, operand);
        }
    }

    public static boolean contains(String sign) {
        return BY_SIGN.containsKey(sign);
    }

    public static Operand valueOfSign(String sign) {
        if (!BY_SIGN.containsKey(sign)) {
            throw new IllegalArgumentException(
                    "Неверный знак: " + sign + ". Выберите из списка: " + BY_SIGN.keySet()
            );
        }
        return BY_SIGN.get(sign);
    }

    /**
     * Конструктор
     *
     * @param sign - знак
     * @param func - функция
     */
    Operand(String sign, Calculate<Integer, Integer> func) {
        this.sign = sign;
        this.func = func;
    }

    /**
     * Фукция, которая соответствует каждому знаку. Например для "+" будет сумма.
     *
     * @param a - первый параметр
     * @param b - второй
     * @return a operand b
     */
    public Integer calculate(Integer a, Integer b) {
        return func.calculate(a, b);
    }
}
