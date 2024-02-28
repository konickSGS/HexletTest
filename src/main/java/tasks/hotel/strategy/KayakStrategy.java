package tasks.hotel.strategy;

import java.util.HashMap;
import java.util.Map;

public class KayakStrategy implements Strategy {
    @Override
    public Map<String, Object> convert(Map<String, Object> hotel) {
        return new HashMap(hotel);
    }
}
