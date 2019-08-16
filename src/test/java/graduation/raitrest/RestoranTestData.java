package graduation.raitrest;

import graduation.raitrest.model.entities.Restaurant;

import java.util.List;

import static graduation.raitrest.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestoranTestData {
    public static final int RESTAURANT_ID = START_SEQ + 7;
    public static final Restaurant RESTAURANT_STAR = new Restaurant(RESTAURANT_ID, "Star");
    public static final Restaurant RESTAURANT_PEARL = new Restaurant(RESTAURANT_ID + 1, "Pearl");
    public static final Restaurant RESTAURANT_SUPER_STAR = new Restaurant(RESTAURANT_ID + 2, "Super Star");
    //  У черной жемчужины два хозяина - манаджер и манажер_1
    public static final Restaurant RESTAURANT_BLACK_PEARL_MAN_1 = new Restaurant(RESTAURANT_ID + 3, "Black Pearl");
    public static final Restaurant RESTAURANT_BLACK_PEARL_MAN = new Restaurant(RESTAURANT_ID + 4, "Black Pearl");


    public static List<Restaurant> restaurantList = List.of(RESTAURANT_STAR, RESTAURANT_PEARL, RESTAURANT_SUPER_STAR,
            RESTAURANT_BLACK_PEARL_MAN_1, RESTAURANT_BLACK_PEARL_MAN);

    public static Restaurant getCreated() {
        return new Restaurant(null, "New restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Update Star");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
        //  assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        //  assertThat(actual).usingElementComparatorIgnoringFields("registered").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}
