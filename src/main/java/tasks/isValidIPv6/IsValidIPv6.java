package tasks.isValidIPv6;

import java.util.Collections;
import java.util.stream.Stream;

public class IsValidIPv6 {
    /**
     * Проверка строки на то, что она 16ричное число.
     * Тупой способ через try catch
     *
     * @param s - строка
     * @return true, если 16ричная
     */
    public static boolean isHexadecimal(String s) {
        try {
            // Attempt to parse the string as an integer in base 16 (hexadecimal)
            Integer.parseInt(s, 16);
            return true; // If successful, the string is hexadecimal
        } catch (NumberFormatException e) {
            return false; // If an exception is caught, the string is not hexadecimal
        }
    }

    /**
     * Необходимый метод.
     *
     * @param ip6 ip6 строка
     * @return является ли ip6 строка валидной
     */
    public static boolean isValidIPv6(String ip6) {
        if (!trivialCheckFormat(ip6))
            return false;
        ip6 = normalizeIp6(ip6);
        return Stream.of(ip6.split(":"))
                .allMatch(IsValidIPv6::isHexadecimal);
    }

    /**
     * Нормализация ip6. То есть замена "::" на строку вида "0:0:...:0"
     *
     * @param ip6 ip6 строка
     * @return - нормализованный ip6
     */
    private static String normalizeIp6(String ip6) {
        if (!ip6.contains("::"))
            return ip6;

        if (ip6.endsWith("::"))
            ip6 = ip6 + "0";
        if (ip6.startsWith("::"))
            ip6 = "0" + ip6;

        int colonCount = countSubstring(ip6, ":");
        String normalizeColon = String.join(
                "0",
                Collections.nCopies(7 + 2 - colonCount, ":")
        );
        return ip6.replace("::", normalizeColon);
    }

    /**
     * Метод для проверки тривиальных проверок ip6.
     * Например, если несколько вхождений "::" или количество двоеточий больше 7.
     *
     * @param ip6 - ip6 строка
     * @return - true, если строка пройдет тривиальные проверки
     */
    private static boolean trivialCheckFormat(String ip6) {
        ip6 = ip6.trim();
        int colonCount = countSubstring(ip6, ":");
        if (colonCount < 2 || colonCount > 7)
            return false;
        // Количество двойных двоеточий. Их не должно быть больше двух
        int doubleColonCount = countSubstring(ip6, "::");
        if (doubleColonCount > 1)
            return false;

        // Одиночных двоеточия не должны быть в начале или в конце
        if (ip6.endsWith(":") && !ip6.endsWith("::"))
            return false;
        if (ip6.startsWith(":") && !ip6.startsWith("::"))
            return false;
        if (doubleColonCount == 1 && colonCount == 7)
            return false;

        return Stream.of(ip6.split(":")).allMatch(s -> s.length() <= 4);
    }

    /**
     * Кастомный метод для подсчета вхождения подстроки в строку
     *
     * @param str       - строка
     * @param substring - подстрока
     * @return - количество вхождений
     */
    private static int countSubstring(String str, String substring) {
        int count = -1;
        int lastIndex = -1;
        do {
            lastIndex = str.indexOf(substring, lastIndex + 1);
            count++;
        } while (lastIndex != -1);
        return count;
    }
}
