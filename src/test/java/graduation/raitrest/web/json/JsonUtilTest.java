package graduation.raitrest.web.json;

import graduation.raitrest.model.entities.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.List;

import static graduation.raitrest.RestoranTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {
    @Test
    void readWriteValue() throws Exception {
        String json = JsonUtil.writeValue(RESTAURANT_STAR);
        System.out.println(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        assertMatch(restaurant, RESTAURANT_STAR);
    }

    @Test
    void readWriteValues() throws Exception {
        String json = JsonUtil.writeValue(restaurantList);
        System.out.println(json);
        List<Restaurant> meals = JsonUtil.readValues(json, Restaurant.class);
        assertMatch(meals, restaurantList);
    }

}