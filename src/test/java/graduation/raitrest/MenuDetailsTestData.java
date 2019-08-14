package graduation.raitrest;

import graduation.raitrest.model.entities.MenuDetails;

import java.util.Date;
import java.util.List;

import static graduation.raitrest.RestoranTestData.*;
import static graduation.raitrest.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuDetailsTestData {
    // TODAY
    public static final int MENU_DETAILS_ID = 100;
    public static final MenuDetails MENU_DETAILS_STAR_TODAY_1 = new MenuDetails(MENU_DETAILS_ID, "1. meal dish first",
            new Date(), RESTORAN_STAR, ADMIN);
    public static final MenuDetails MENU_DETAILS_STAR_TODAY_2 = new MenuDetails(MENU_DETAILS_ID + 1, "2. meal dish second",
            new Date(), RESTORAN_STAR, ADMIN);
    public static final MenuDetails MENU_DETAILS_STAR_TODAY_3 = new MenuDetails(MENU_DETAILS_ID + 2, "3. meal dish third",
            new Date(), RESTORAN_PEARL, ADMIN);
    public static final MenuDetails MENU_DETAILS_PEARL_TODAY_1 = new MenuDetails(MENU_DETAILS_ID + 3, "1. meal dish first",
            new Date(), RESTORAN_PEARL, MANAGER);
    public static final MenuDetails MENU_DETAILS_PEARL_TODAY_2 = new MenuDetails(MENU_DETAILS_ID + 4, "2. meal dish second",
            new Date(), RESTORAN_PEARL, MANAGER);
    public static final MenuDetails MENU_DETAILS_PEARL_TODAY_3 = new MenuDetails(MENU_DETAILS_ID + 5, "3. meal dish third",
            new Date(), RESTORAN_PEARL, MANAGER);
    // BLACK_PEARL - !! ДВА ХОЗЯИНА !!,  - MANAGER, MANAGER_1
    public static final MenuDetails MENU_DETAILS_BLACK_PEARL_TODAY_1 = new MenuDetails(MENU_DETAILS_ID + 6, "1. meal dish first",
            new Date(), RESTORAN_BLACK_PEARL_MAN_1, MANAGER_1);
    public static final MenuDetails MENU_DETAILS_BLACK_PEARL_TODAY_2 = new MenuDetails(MENU_DETAILS_ID + 7, "2. meal dish second",
            new Date(), RESTORAN_BLACK_PEARL_MAN_1, MANAGER_1);

     public static final MenuDetails MENU_DETAILS_SUPER_STAR_TODAY_1 = new MenuDetails(MENU_DETAILS_ID + 8, "1. meal dish first",
            new Date(), RESTORAN_SUPER_STAR, ADMIN);
    public static final MenuDetails MENU_DETAILS_SUPER_STAR_TODAY_2 = new MenuDetails(MENU_DETAILS_ID + 9, "2. meal dish second",
            new Date(), RESTORAN_SUPER_STAR, ADMIN);
    // YESTERDAY
    public static final MenuDetails MENU_DETAILS_PEARL_YESTERDAY_1 = new MenuDetails(MENU_DETAILS_ID + 10, "1-1. meal dish first",
            new Date(), RESTORAN_PEARL, MANAGER);
    public static final MenuDetails MENU_DETAILS_PEARL_YESTERDAY_2 = new MenuDetails(MENU_DETAILS_ID + 11, "2-1. meal dish second",
            new Date(), RESTORAN_PEARL, MANAGER);
    public static final MenuDetails MENU_DETAILS_PEARL_YESTERDAY_3 = new MenuDetails(MENU_DETAILS_ID + 12, "3-1. meal dish third",
            new Date(), RESTORAN_PEARL, MANAGER);

    public static final MenuDetails MENU_DETAILS_STAR_YESTERDAY_1 = new MenuDetails(MENU_DETAILS_ID + 13, "1-1. meal dish first",
            new Date(), RESTORAN_STAR, ADMIN);
    public static final MenuDetails MENU_DETAILS_STAR_YESTERDAY_2 = new MenuDetails(MENU_DETAILS_ID + 14, "2-1. meal dish second",
            new Date(), RESTORAN_STAR, ADMIN);
    public static final MenuDetails MENU_DETAILS_STAR_YESTERDAY_3 = new MenuDetails(MENU_DETAILS_ID + 15, "3-1. meal dish third",
            new Date(), RESTORAN_PEARL, ADMIN);

    // BLACK_PEARL - !! ДВА ХОЗЯИНА !!,  - MANAGER, MANAGER_1
    public static final MenuDetails MENU_DETAILS_BLACK_PEARL_YESTERDAY_1 = new MenuDetails(MENU_DETAILS_ID + 16, "1-1. meal dish first",
            new Date(), RESTORAN_BLACK_PEARL_MAN_1, MANAGER_1);
    public static final MenuDetails MENU_DETAILS_BLACK_PEARL_YESTERDAY_2 = new MenuDetails(MENU_DETAILS_ID + 17, "2-1. meal dish second",
            new Date(), RESTORAN_BLACK_PEARL_MAN_1, MANAGER_1);

    public static final MenuDetails MENU_DETAILS_SUPER_STAR_YESTERDAY_1 = new MenuDetails(MENU_DETAILS_ID + 18, "1-1. meal dish first",
            new Date(), RESTORAN_SUPER_STAR, ADMIN);
    public static final MenuDetails MENU_DETAILS_SUPER_STAR_YESTERDAY_2 = new MenuDetails(MENU_DETAILS_ID + 19, "2-1. meal dish second",
            new Date(), RESTORAN_SUPER_STAR, ADMIN);

    // THE DAY BEFORE YESTERDAY
    public static final MenuDetails MENU_DETAILS_PEARL_BEFORE_YESTERDAY_1 = new MenuDetails(MENU_DETAILS_ID + 20, "1-2. meal dish first",
            new Date(), RESTORAN_PEARL, MANAGER);
    public static final MenuDetails MENU_DETAILS_PEARL_BEFORE_YESTERDAY_2 = new MenuDetails(MENU_DETAILS_ID + 21, "2-2. meal dish second",
            new Date(), RESTORAN_PEARL, MANAGER);
    public static final MenuDetails MENU_DETAILS_PEARL_BEFORE_YESTERDAY_3 = new MenuDetails(MENU_DETAILS_ID + 22, "3-2. meal dish third",
            new Date(), RESTORAN_PEARL, MANAGER);

    public static final MenuDetails MENU_DETAILS_STAR_BEFORE_YESTERDAY_1 = new MenuDetails(MENU_DETAILS_ID + 23, "1-2. meal dish first",
            new Date(), RESTORAN_STAR, ADMIN);
    public static final MenuDetails MENU_DETAILS_STAR_BEFORE_YESTERDAY_2 = new MenuDetails(MENU_DETAILS_ID + 24, "2-2. meal dish second",
            new Date(), RESTORAN_STAR, ADMIN);
    public static final MenuDetails MENU_DETAILS_STAR_BEFORE_YESTERDAY_3 = new MenuDetails(MENU_DETAILS_ID + 25, "3-2. meal dish third",
            new Date(), RESTORAN_PEARL, ADMIN);

    // BLACK_PEARL - !! ДВА ХОЗЯИНА !!,  - MANAGER, MANAGER_1 - ПОМЕНЯЛ МЕНЕДЖЕРА
    public static final MenuDetails MENU_DETAILS_BLACK_PEARL_BEFORE_YESTERDAY_1 = new MenuDetails(MENU_DETAILS_ID + 26, "1-2. meal dish first",
            new Date(), RESTORAN_BLACK_PEARL_MAN_1, MANAGER);
    public static final MenuDetails MENU_DETAILS_BLACK_PEARL_BEFORE_YESTERDAY_2 = new MenuDetails(MENU_DETAILS_ID + 27, "2-2. meal dish second",
            new Date(), RESTORAN_BLACK_PEARL_MAN_1, MANAGER);

    public static final MenuDetails MENU_DETAILS_SUPER_STAR_BEFORE_YESTERDAY_1 = new MenuDetails(MENU_DETAILS_ID + 28, "1-2. meal dish first",
            new Date(), RESTORAN_SUPER_STAR, ADMIN);
    public static final MenuDetails MENU_DETAILS_SUPER_STAR_BEFORE_YESTERDAY_2 = new MenuDetails(MENU_DETAILS_ID + 29, "2-2. meal dish second",
            new Date(), RESTORAN_SUPER_STAR, ADMIN);

    public static final List<MenuDetails> MENU_DETAILS_LIST = List.of(MENU_DETAILS_STAR_TODAY_1,
            MENU_DETAILS_STAR_TODAY_2,MENU_DETAILS_STAR_TODAY_3,MENU_DETAILS_PEARL_TODAY_1,MENU_DETAILS_PEARL_TODAY_2,
            MENU_DETAILS_PEARL_TODAY_3,MENU_DETAILS_BLACK_PEARL_TODAY_1,MENU_DETAILS_BLACK_PEARL_TODAY_2,
            MENU_DETAILS_SUPER_STAR_TODAY_1,MENU_DETAILS_SUPER_STAR_TODAY_2,
            MENU_DETAILS_PEARL_YESTERDAY_1,MENU_DETAILS_PEARL_YESTERDAY_2,MENU_DETAILS_PEARL_YESTERDAY_3,
            MENU_DETAILS_STAR_YESTERDAY_1,MENU_DETAILS_STAR_YESTERDAY_2,MENU_DETAILS_STAR_YESTERDAY_3,
            MENU_DETAILS_SUPER_STAR_YESTERDAY_1,MENU_DETAILS_SUPER_STAR_YESTERDAY_2,
            MENU_DETAILS_PEARL_BEFORE_YESTERDAY_1,MENU_DETAILS_PEARL_BEFORE_YESTERDAY_2,MENU_DETAILS_PEARL_BEFORE_YESTERDAY_3,
            MENU_DETAILS_STAR_BEFORE_YESTERDAY_1,MENU_DETAILS_STAR_BEFORE_YESTERDAY_2,MENU_DETAILS_STAR_BEFORE_YESTERDAY_3,
            MENU_DETAILS_BLACK_PEARL_BEFORE_YESTERDAY_1,MENU_DETAILS_BLACK_PEARL_BEFORE_YESTERDAY_2,
            MENU_DETAILS_SUPER_STAR_BEFORE_YESTERDAY_1,MENU_DETAILS_SUPER_STAR_BEFORE_YESTERDAY_2);

    public static void assertMatch(MenuDetails actual, MenuDetails expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime", "restoran","manager");
    }

    public static void assertMatch(Iterable<MenuDetails> actual, MenuDetails... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<MenuDetails> actual, Iterable<MenuDetails> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dateTime", "restoran","manager").isEqualTo(expected);
    }

    public static void assertMatchFull(MenuDetails actual, MenuDetails expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime");
    }

}
