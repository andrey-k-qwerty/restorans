package graduation.raitrest;

import graduation.raitrest.model.entities.Restaurant;

import java.util.Date;
import java.util.List;

import static graduation.raitrest.UserTestData.*;
import static graduation.raitrest.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hsqldb.Tokens.CURRENT_DATE;

public class RestoranTestData {
    public static final int RESTAURANT_ID = START_SEQ + 9;
    // У   манаджера два ресторана star и super star
    public static final Restaurant RESTAURANT_STAR = new Restaurant(RESTAURANT_ID, "Star",
            "Адресс 1, тел 111-111-111", "Директор 1", new Date(), "Звезда", MANAGER);
    public static final Restaurant RESTAURANT_PEARL = new Restaurant(RESTAURANT_ID + 1, "Pearl",
            "Адресс 2, тел 222-222-222", "Директор 2", new Date(), "Жемчужина", MANAGER_1);
    public static final Restaurant RESTAURANT_SUPER_STAR = new Restaurant(RESTAURANT_ID + 2, "Super Star",
            "Адресс 3, тел 333-333-333", "Директор 3", new Date(), "Супер Звезда", MANAGER);
    //  У черной жемчужины два хозяина - манаджер и манажер_1
    public static final Restaurant RESTAURANT_BLACK_PEARL_MAN = new Restaurant(RESTAURANT_ID + 3, "Black Pearl",
             "Адресс 4, тел 444-444-444", "Директор 4", new Date(), "Черная Жемчужина", MANAGER_2);


    public static List<Restaurant> restaurantList = List.of(RESTAURANT_STAR, RESTAURANT_PEARL, RESTAURANT_SUPER_STAR,
            RESTAURANT_BLACK_PEARL_MAN);

    public static Restaurant getCreated() {
        return new Restaurant( "Новый ресторан",
                "Новый Адресс , тел 111-111-111", "Новый директор", new Date(), "Новый ресторан", MANAGER);
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Super Super Star",
                "Адресс 1, тел 111-111-111", "Директор 1", new Date(), "Звезда", MANAGER);
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered","manager");
        //  assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
          assertThat(actual).usingElementComparatorIgnoringFields("registered","manager").isEqualTo(expected);
       // assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}
