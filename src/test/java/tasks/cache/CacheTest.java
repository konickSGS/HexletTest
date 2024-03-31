package tasks.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CacheTest {
    @Test
    void testCache() {
        var cache = new Cache<String, String>(2);

        cache.put("praesent", "imperdiet et commodo vulputate");
        cache.put("amet erat", "in tempus sit amet");

        Assertions.assertEquals(cache.get("praesent"), "imperdiet et commodo vulputate");
        Assertions.assertEquals(cache.get("amet erat"), "in tempus sit amet");

        cache.put("justo sit", "auctor sed tristique in");

        Assertions.assertEquals(cache.get("justo sit"), "auctor sed tristique in");
        Assertions.assertEquals(cache.get("amet erat"), "in tempus sit amet");
        Assertions.assertEquals(cache.get("praesent"), null);

        cache.put("sed", "vel ipsum praesent blandit");

        Assertions.assertEquals(cache.get("sed"), "vel ipsum praesent blandit");
        Assertions.assertEquals(cache.get("justo sit"), "auctor sed tristique in");
        Assertions.assertEquals(cache.get("amet erat"), null);

        cache.put("sed", "another value");

        Assertions.assertEquals(cache.get("sed"), "another value");
        Assertions.assertEquals(cache.get("justo sit"), "auctor sed tristique in");

        Assertions.assertEquals(cache.get("not exists"), null);
    }
}
