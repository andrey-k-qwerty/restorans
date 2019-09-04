package graduation.raitrest.web.menu;

import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.model.to.MenuDetailTo;
import graduation.raitrest.web.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static graduation.raitrest.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MenuDetailRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuDetailRestController extends AbstractMenuDetailController {
    public static final String REST_URL = "/rest/profile/menu";

    @Override
    @GetMapping("/{id}")
    public MenuDetails get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping("/all")
    public List<MenuDetailTo> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping()
    public List<MenuDetails> getAllCurrentDay() {
        return super.getAllCurrentDay();
    }

    @PostMapping(value = "/{restaurantID}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuDetails> createWithLocation(@RequestBody MenuDetails menuDetails, @PathVariable int restaurantID) {
        MenuDetails created = super.create(menuDetails,restaurantID);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody MenuDetails menuDetails, @PathVariable int id) {
        super.update(menuDetails, id);
    }



}