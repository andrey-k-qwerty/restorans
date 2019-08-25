package graduation.raitrest.service;

import graduation.raitrest.RestoranTestData;
import graduation.raitrest.model.entities.Vote;
import graduation.raitrest.model.to.Rating;
import graduation.raitrest.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static graduation.raitrest.RestoranTestData.RESTAURANT_ID;
import static graduation.raitrest.RestoranTestData.RESTAURANT_PEARL;
import static graduation.raitrest.UserTestData.*;
import static graduation.raitrest.VotesTestData.*;
import static graduation.raitrest.VotesTestData.assertMatch;
import static org.junit.Assert.*;

public class VoteServiceTest extends AbstractServiceTest {
    @Autowired
    public VoteService voteService;

    @Test
    public void get() {
        Vote vote = voteService.get(VOTES_ID);
        assertMatch(vote, VOTES_1_YESTERDAY);

        vote = voteService.get(VOTES_ID + 4, USER_ID);
        assertMatch(vote, VOTES_1_TODAY);

        vote = voteService.getCurrentVoise(USER_1_ID);
        assertMatch(vote, VOTES_2_TODAY);

        vote = voteService.getCurrentVoise(USER_3_ID);
        Assert.assertNull(vote);


    }

    @Test
    public void getRating() {
        List<Rating> currentRating = voteService.getCurrentRating();
        Assert.assertEquals(2, currentRating.size());

        currentRating = voteService.getAllRating();
        Assert.assertEquals(4, currentRating.size());

    }

    @Test
    public void getAll() {
        List<Vote> allVotes = voteService.getAll();
        List<Vote> collect = VOTE_LIST.stream().sorted((o1, o2) -> o2.getDateTime().toLocalDate().compareTo(o1.getDateTime().toLocalDate())).collect(Collectors.toList());
        assertMatch(allVotes, collect);

        allVotes = voteService.getAll(USER_ID);
        collect = VOTE_LIST.stream().filter(vote -> vote.getUser().getId() == USER_ID)
                .sorted((o1, o2) -> o2.getDateTime().toLocalDate().compareTo(o1.getDateTime().toLocalDate()))
                .collect(Collectors.toList());
        assertMatch(allVotes, collect);
    }

    @Test
    public void create() {
        Vote newVote = new Vote();
        newVote.setDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 20)));
        Vote voteCreate = voteService.create(newVote, RESTAURANT_ID, USER_3_ID);
        newVote.setId(voteCreate.getId());
        assertMatch(newVote, voteService.get(voteCreate.getId()));
    }

    @Test
    public void createWithRestaurant() {
        Vote newVote = getCreated();
        // голос уже с рестораном внутри
        Vote voteCreate = voteService.create(newVote, USER_3_ID);
        newVote.setId(voteCreate.getId());
        assertMatch(newVote, voteService.get(voteCreate.getId()));

    }

    @Test
    public void update() {
        Vote voteUpdate = voteService.get(VOTES_ID + 5, USER_1_ID);
        voteUpdate.setRestaurant(RESTAURANT_PEARL);
        voteService.update(voteUpdate , USER_1_ID);
        assertMatch(voteUpdate, voteService.get(voteUpdate.getId()));
      //  RestoranTestData.assertMatch(voteUpdate.getRestaurant(), voteService.get(voteUpdate.getId()).getRestaurant());
        List<Rating> currentRating = voteService.getCurrentRating();
        Assert.assertEquals(1, currentRating.size());


    }

    @Test
    public void delete()  {
        Vote  vote = voteService.getCurrentVoise(USER_2_ID);
        Assert.assertNotNull(vote);;
        voteService.delete(VOTES_ID + 6, USER_2_ID);
        vote = voteService.getCurrentVoise(USER_2_ID);
        Assert.assertNull(vote);


    }

    @Test
    public void deleteNotFound()  {
        thrown.expect(NotFoundException.class);
        voteService.delete(1, MANAGER_ID);
    }


}