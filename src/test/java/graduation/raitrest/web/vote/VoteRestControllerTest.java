package graduation.raitrest.web.vote;

import graduation.raitrest.model.entities.Vote;
import graduation.raitrest.model.to.Rating;
import graduation.raitrest.service.VoteService;
import graduation.raitrest.web.AbstractControllerTest;
import graduation.raitrest.web.SecurityUtil;
import graduation.raitrest.web.converter.DateTimeFormatters;
import graduation.raitrest.web.json.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static graduation.raitrest.RestoranTestData.*;
import static graduation.raitrest.TestUtil.*;
import static graduation.raitrest.UserTestData.*;
import static graduation.raitrest.UserTestData.USER_1_ID;
import static graduation.raitrest.VotesTestData.*;
import static graduation.raitrest.VotesTestData.contentJsonTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    private VoteService service;


    @Test
    void get() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + VOTES_ID).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Vote.class), VOTES_1_YESTERDAY));

    }

    @Test
    void getUnauth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + VOTES_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {

        // проверяем что голос есть
        Vote  vote = service.getTodayVote(USER_2_ID);
        Assertions.assertNotNull(vote);;
        // удаляем
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + (VOTES_ID + 6)).with(userHttpBasic(USER_2)))
                .andExpect(status().isNoContent());
        // проверяенм что голоса нет
        vote = service.getTodayVote(USER_2_ID);
        Assertions.assertNull(vote);
    }

    @Test
    void getAllByUser() throws Exception {
        SecurityUtil.setAuthUserId(USER_ID);

        List<Vote>  collect = VOTE_LIST.stream().filter(vote -> vote.getUser().getId() == USER_ID)
                .sorted((o1, o2) -> o2.getDateTime().toLocalDate().compareTo(o1.getDateTime().toLocalDate()))
                .collect(Collectors.toList());

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(collect));


    }

    @Test
    void getRatingRestaurants() throws Exception {

        ResultMatcher matcher = contentJsonTo(service.getAllRating());
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "rating/all").with(userHttpBasic(USER_3)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(matcher);

    }
    @Test
    void filter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL +  "rating/all")
//                .param("startDate", "2019-09-03")
//                .param("endDate", "2019-09-03"))
                .param("startDate",LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .param("endDate", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)).with(userHttpBasic(USER_1)))

                .andExpect(status().isOk()).andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonTo(service.getTodayRating()));
    }

    @Test
    void getCurrentDayRatingRestaurants() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "rating").with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonTo(service.getTodayRating()));
    }

    @Test
    void update() throws Exception {

        Vote voteUpdate = service.get(VOTES_ID + 5, USER_1_ID);
        voteUpdate.setRestaurant(RESTAURANT_PEARL);

        mockMvc.perform(MockMvcRequestBuilders.put(
                REST_URL + (VOTES_ID + 5)).contentType(MediaType.APPLICATION_JSON).with(userHttpBasic(USER_1))
                .content(JsonUtil.writeValue(voteUpdate)))
                .andExpect(status().isNoContent());

        Vote expected = service.get(voteUpdate.getId());
        assertMatch(voteUpdate, expected);
    }

    @Test
    void createWithLocation() throws Exception {

        Vote newVote =new Vote(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 20)), null, null);

        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT_ID )
                .with(userHttpBasic(USER_3))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andDo(print());

        Vote returned = readFromJson(action, Vote.class);
        newVote.setId(returned.getId());
        assertMatch(returned, newVote);

    }
}