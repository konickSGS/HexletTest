package tasks.hotel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import tasks.hotel.strategy.BookingStrategy;
import tasks.hotel.strategy.Strategy;
import tasks.hotel.strategy.KayakStrategy;
import tasks.hotel.strategy.OstrovokStrategy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Предикат для min и max. Также учитывает случай, когда предиката нет.
 * А это значит, что мы просто min и max как максимальные возможные варианты и всегда будет true.
 */
class MinMaxPredicate implements Predicate<Double> {
    private Double min = 0.0;
    private Double max = Double.MAX_VALUE;

    public MinMaxPredicate() {
    }

    public MinMaxPredicate(Double min, Double max) {
        this.min = min;
        this.max = max;
    }

    public MinMaxPredicate(Map<String, Integer> map) {
        if (map != null) {
            if (map.containsKey("min")) {
                this.min = (double) map.get("min");
            }
            if (map.containsKey("max")) {
                this.max = (double) map.get("max");
            }
        }
    }

    @Override
    public boolean test(Double number) {
        return number >= min && number <= max;
    }
}

/**
 * Так как в задаче обязательно использовать Map, а не кастомный объект, то приходится сооружать велосипед.
 */
public class Gateway {
    private List<Map<String, Object>> hotelAndService;
    private Map<String, Strategy> services;

    /**
     * Метод, который переводит формат в json в удобный, где к каждому отелю прикрепляется сервис, а не наоборот
     *
     * @param hotelsMap - map из Json файла
     * @return - лист мап, где к каждому отелю прикреплен service
     */
    private static List<Map<String, Object>> getHotels(Map<String, Object> hotelsMap) {
        String currentService = (String) hotelsMap.get("service");
        List<Map<String, Object>> hotels = (List<Map<String, Object>>) hotelsMap.get("hotels");

        return hotels.stream()
                .map(hotelMap -> {
                    Map<String, Object> hotel = new HashMap<>();
                    hotel.put("hotel", hotelMap);
                    hotel.put("service", currentService);
                    return hotel;
                }).collect(Collectors.toList());
    }

    public Gateway() {
        List<Map<String, Object>> hotelsByService = getData("data.json");
        hotelAndService = hotelsByService.stream()
                .map(Gateway::getHotels)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        services = new HashMap<>();
        services.put("ostrovok", new OstrovokStrategy());
        services.put("kayak", new KayakStrategy());
        services.put("booking", new BookingStrategy());
    }

    private static List<Map<String, Object>> getData(String fileName) {
        Path filePath = Paths.get("src", "main", "resources", fileName).toAbsolutePath().normalize();
        ObjectMapper mapper = new ObjectMapper();
        try {
            var content = Files.readString(filePath).trim();
            return mapper.readValue(content, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<Map<String, Object>> findAll() {
        return hotelAndService.stream()
                .map(hotelMap -> {
                    String currentService = (String) hotelMap.get("service");
                    Strategy strategy = services.get(currentService);
                    Map<String, Object> hotel = (Map<String, Object>) hotelMap.get("hotel");

                    return Map.of("service", currentService, "hotel", strategy.convert(hotel));
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> findAll(Map<String, Integer> minMaxPredicate) {
        MinMaxPredicate predicate = new MinMaxPredicate(minMaxPredicate);

        return findAll()
                .stream()
                .filter(hotelMap -> {
                    Map<String, Object> hotel = (Map<String, Object>) hotelMap.get("hotel");
                    Double cost = (Double) hotel.get("cost");

                    return predicate.test(cost);
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Gateway gateway = new Gateway();
        Map<String, Integer> predicates = Map.of(
                "min", 600,
                "max", 700
        );

        gateway.findAll().forEach(System.out::println);
        System.out.println("--------------");
        gateway.findAll(predicates).forEach(System.out::println);
    }
}
