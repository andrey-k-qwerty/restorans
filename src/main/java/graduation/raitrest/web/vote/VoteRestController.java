package graduation.raitrest.web.vote;

import graduation.raitrest.model.entities.Vote;
import graduation.raitrest.model.to.Rating;
import graduation.raitrest.model.to.VoteTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {
    public static final String REST_URL = "/rest/profile/votes";

    @Override
    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<Vote> getAllByUser() {
        return super.getAllByUser();
    }

    @Override
    @GetMapping("/rating/all")
    public List<Rating> getRatingRestaurants(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        return super.getRatingRestaurants(startDate, endDate);
    }

    @Override
    @GetMapping("/rating")
    public List<Rating> getTodayRatingRestaurants() {
        return super.getTodayRatingRestaurants();
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody VoteTo voteTo,@PathVariable int id) {
        Vote vote = super.get(id);
        // when updating set current time
        vote.setDateTime(LocalDateTime.now());

        super.update(vote,voteTo.getRestaurantID(), id); // voteTo.id().
    }

    @PostMapping(/*value = "/{restaurantID}",*/consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteTo> createWithLocation(@Valid @RequestBody VoteTo voteTo) {
        Vote created = super.create(voteTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        voteTo.setId(created.getId());
        voteTo.setDateTime(created.getDateTime());

        return ResponseEntity.created(uriOfNewResource).body(voteTo);
    }


}
