package graduation.raitrest.web.vote;

import graduation.raitrest.model.entities.Vote;
import graduation.raitrest.model.to.Rating;
import graduation.raitrest.service.VoteService;
import graduation.raitrest.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static graduation.raitrest.util.DateTimeUtil.adjustEndDateTime;
import static graduation.raitrest.util.DateTimeUtil.adjustStartDateTime;
import static graduation.raitrest.util.ValidationUtil.assureIdConsistent;
import static graduation.raitrest.util.ValidationUtil.checkNew;

public class AbstractVoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private VoteService service;

    public Vote get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get vote {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete vote {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Vote> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll votes for user {}", userId);
        return service.getAll(userId);
    }

    public Vote create(Vote vote) {
        int userId = SecurityUtil.authUserId();
        checkNew(vote);
        log.info("create {} for user {}", vote, userId);
        return service.create(vote, userId);
    }

    public void update(Vote vote, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(vote, id);
        log.info("update {} for user {}", vote, userId);
        service.update(vote, userId);
    }

    public List<Rating> getRatingRestaurants(LocalDate startDate, LocalDate endDate) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, userId);
        // рейтиг ресторанов смотреть можно всем, поєтому просто проверяем  зарегистрированный ли пользователь в системе
        //  пока так
        if (userId != 0)
            return service.getRating(adjustStartDateTime(startDate), adjustEndDateTime(endDate));
        else
            return null;


    }

    public List<Rating> getCurrentDayRatingRestaurants() {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}");
        // рейтиг ресторанов смотреть можно всем, поєтому просто проверяем  зарегистрированный ли пользователь в системе
        //  пока так
        if (userId != 0)
            return service.getCurrentRating();
        else
            return null;


    }

}