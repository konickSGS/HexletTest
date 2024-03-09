package tasks.scrabble;

import java.util.Map;
import java.util.stream.Collectors;

public class Scrabble {
    public static boolean scrabble(CharSequence letters, CharSequence str) {
        boolean isImportantCase = true;
        Map<Character, Long> lettersFreq = getFrequency(letters, isImportantCase);
        Map<Character, Long> strFreq = getFrequency(str, isImportantCase);

        for (var letter : strFreq.keySet()) {
            if (!lettersFreq.containsKey(letter)) {
                return false;
            }
            if (lettersFreq.get(letter) < strFreq.get(letter)) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Long> getFrequency(CharSequence s, Boolean isImportantCase) {
        if (isImportantCase) {
            s = s.chars()
                    .mapToObj(c -> (char) c)
                    .map(Character::toLowerCase)
                    .map(String::valueOf)
                    .collect(Collectors.joining(""));
        }
        return getFrequency(s);
    }

    private static Map<Character, Long> getFrequency(CharSequence s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .map(Character::toLowerCase)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));
    }
}
