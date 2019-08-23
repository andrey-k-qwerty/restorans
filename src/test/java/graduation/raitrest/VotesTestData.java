package graduation.raitrest;

import graduation.raitrest.model.entities.Vote;

import java.time.LocalDateTime;
import java.util.List;

import static graduation.raitrest.RestoranTestData.*;
import static graduation.raitrest.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class VotesTestData {
    public static final int VOTES_ID = 100013;
    public static final Vote VOTES_1_YESTERDAY = new Vote(VOTES_ID, LocalDateTime.now().minusDays(1l), USER, RESTAURANT_STAR);
    public static final Vote VOTES_2_YESTERDAY = new Vote(VOTES_ID + 1, LocalDateTime.now().minusDays(1l), USER_1, RESTAURANT_PEARL);
    public static final Vote VOTES_3_YESTERDAY = new Vote(VOTES_ID + 2, LocalDateTime.now().minusDays(1l), USER_2, RESTAURANT_SUPER_STAR);
    public static final Vote VOTES_4_YESTERDAY = new Vote(VOTES_ID + 3, LocalDateTime.now().minusDays(1l), USER, RESTAURANT_BLACK_PEARL);

    public static final Vote VOTES_1_TODAY = new Vote(VOTES_ID + 4, LocalDateTime.now().minusDays(1l), USER, RESTAURANT_PEARL);
    public static final Vote VOTES_2_TODAY = new Vote(VOTES_ID + 5, LocalDateTime.now().minusDays(1l), USER_1, RESTAURANT_SUPER_STAR);
    public static final Vote VOTES_3_TODAY = new Vote(VOTES_ID + 6, LocalDateTime.now().minusDays(1l), USER_2, RESTAURANT_PEARL);
    public static final Vote VOTES_4_TODAY = new Vote(VOTES_ID + 7, LocalDateTime.now().minusDays(1l), USER, RESTAURANT_STAR);


    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime","user","restaurant"); //,"user","restaurant"
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dateTime","user","restaurant").isEqualTo(expected);
    }
}
