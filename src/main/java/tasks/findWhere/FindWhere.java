package tasks.findWhere;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindWhere {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> whereBook) {
        return books.stream()
                .filter(book -> {
                    for (var key: whereBook.keySet()) {
                        if (!Objects.equals(book.get(key), whereBook.get(key))) {
                            return false;
                        }
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
