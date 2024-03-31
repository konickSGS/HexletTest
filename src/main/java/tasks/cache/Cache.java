package tasks.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<KEY, VALUE> {
    private Integer capacity;
    private Map<KEY, VALUE> cache;
    public Cache(Integer capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity);
    }

    public void put(KEY key, VALUE value) {
        if (!cache.containsKey(key) && cache.size() == capacity) {
            deleteEldestElement();
        }
        cache.put(key, value);
    }

    public VALUE get(KEY key) {
        return cache.get(key);
    }

    private void deleteEldestElement() {
        var firstEntry = cache.entrySet().iterator().next();
        cache.remove(firstEntry.getKey());
    }
}
