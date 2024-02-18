package tasks.longestLength;

import java.util.HashSet;
import java.util.Set;

public class LongestLength {
    public static int getLongestLength(String str) {
        Set<Character> chars = new HashSet<>();
        int startIndex = 0;
        int maxLength = 0;

        for (int i = 0; i < str.length(); ++i) {
            System.out.println(startIndex + " - " + i);
            char ch = str.charAt(i);
            // Если наш chars уже содержит символ, значит появилось повторение.
            // Вычисляем текущий maxLength
            // Идем до него, параллельно убирая промежуточные значения
            if (chars.contains(ch)) {
                maxLength = Math.max(i - startIndex, maxLength);
                char startCh = str.charAt(startIndex);
                while (startCh != ch) {
                    chars.remove(startCh);
                    startIndex++;
                    startCh = str.charAt(startIndex);
                }
                startIndex++;
            } else {
                // chars еще не содержало данный символ, поэтому просто добавляем
                chars.add(ch);
            }
        }

        // Учитываем случай, что последнее значение строки тоже уникально
        return Math.max(str.length() - startIndex, maxLength);
    }
}
