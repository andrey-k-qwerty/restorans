package graduation.raitrest.service;

import graduation.raitrest.model.entities.Vote;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static graduation.raitrest.UserTestData.USER_ID;
import static graduation.raitrest.VotesTestData.*;
import static org.junit.Assert.*;

public class VoteServiceTest extends AbstractServiceTest {
    @Autowired
    public  VoteService voteService;

    @Test
    public void get() {
        Vote vote = voteService.get(VOTES_ID);
        assertMatch(vote,VOTES_1_YESTERDAY);

         vote = voteService.get(VOTES_ID+4,USER_ID);
        assertMatch(vote,VOTES_1_TODAY);


    }


}