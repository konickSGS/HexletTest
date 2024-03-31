package tasks.findWhere;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class FindWhereTest {
    private static final List<Map<String, String>> BOOKS = List.of(
            Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611"),
            Map.of("title", "Book of Fooos", "author", "FooBar", "year", "1111"),
            Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611"),
            Map.of("title", "Book of Foos Barrrs", "author", "FooBar", "year", "2222"),
            Map.of("title", "Still foooing", "author", "FooBar", "year", "3333"),
            Map.of("title", "HFindWherey Foo", "author", "FooBar", "year", "4444")
    );

    @Test
    void testFindWhere1() {
        List<Map<String, String>> expected = List.of(
                Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611"),
                Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611")
        );

        Map<String, String> where = Map.of("author", "Shakespeare", "year", "1611");
        List<Map<String, String>> actual = FindWhere.findWhere(BOOKS, where);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void testFindWhere2() {
        List<Map<String, String>> expected = List.of(
                Map.of("title", "Still foooing", "author", "FooBar", "year", "3333")
        );
        Map<String, String> where = Map.of("title", "Still foooing", "author", "FooBar", "year", "3333");
        List<Map<String, String>> actual = FindWhere.findWhere(BOOKS, where);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void testFindWhere3() {
        Map<String, String> where = Map.of("title", "Still foooing", "author", "FooBar", "year", "4444");
        List<Map<String, String>> actual = FindWhere.findWhere(BOOKS, where);
        Assertions.assertEquals(actual, List.of());
    }
}
