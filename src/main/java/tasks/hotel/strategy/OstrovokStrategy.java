package tasks.hotel.strategy;

import java.util.Map;

public class OstrovokStrategy implements Strategy {
    @Override
    public Map<String, Object> convert(Map<String, Object> hotel) {
        double ostrovokFee = 0.12; // Plus 12 percents

        return Map.of(
                "name", hotel.get("name"),
                "cost", (double) hotel.get("cost") + (double) hotel.get("cost") * ostrovokFee
        );
    }
}
