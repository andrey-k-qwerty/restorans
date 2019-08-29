package graduation.raitrest.web.restaurant;

import graduation.raitrest.model.entities.Restaurant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {
    public static final String REST_URL = "/rest/restaurant";

    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll");
        return super.getAll();
    }

}
