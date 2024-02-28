package tasks.hotel;

import java.util.List;
import java.util.Map;

public class App {
    public static Map<String, Object> findTheCheapestService(Map<String, Integer> predicate) {
        Gateway gateway = new Gateway();
        List<Map<String, Object>> hotels = gateway.findAll(predicate);

        return getMinHotel(hotels);
    }

    public static Map<String, Object> findTheCheapestService() {
        Gateway gateway = new Gateway();
        List<Map<String, Object>> hotels = gateway.findAll();

        return getMinHotel(hotels);
    }

    private static Map<String, Object> getMinHotel(List<Map<String, Object>> hotels) {
        return hotels.stream().min(
                (hotelMap1, hotelMap2) -> {
                    Map<String, Object> hotel1 = (Map<String, Object>) hotelMap1.get("hotel");
                    Map<String, Object> hotel2 = (Map<String, Object>) hotelMap2.get("hotel");
                    Double cost1 = (Double) hotel1.get("cost");
                    Double cost2 = (Double) hotel2.get("cost");
                    return Double.compare(cost1, cost2);
                }
        ).orElse(null);
    }

    public static void main(String[] args) {
        Map<String, Integer> predicates = Map.of(
                "min", 600,
                "max", 800
        );

        Map<String, Object> result = App.findTheCheapestService(predicates);
        System.out.println(result);
    }
}
