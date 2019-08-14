package graduation.raitrest;

import graduation.raitrest.model.entities.Menu;

import java.util.Date;
import java.util.List;

import static graduation.raitrest.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {
    public static final int MENU_ID = START_SEQ + 20;

//    public static final Menu MENU_STAR = new Menu(MENU_ID, "Star_meal_dinner", new Date(), RestoranTestData.RESTORAN_STAR);
//    public static final Menu MENU_PEARL = new Menu(MENU_ID + 1, "Pearl_meal_dinner", new Date(), RestoranTestData.RESTORAN_PEARL);
//    public static final Menu MENU_STAR_1 = new Menu(MENU_ID + 2, "Star_1_meal_dinner", new Date(), RestoranTestData.RESTORAN_STAR_1);
//    public static final Menu MENU_STAR_2 = new Menu(MENU_ID + 3, "Star_2_meal_dinner", new Date(), RestoranTestData.RESTORAN_STAR_2);
//    public static final Menu MENU_STAR_3 = new Menu(MENU_ID + 4, "Star_3_meal_dinner", new Date(), RestoranTestData.RESTORAN_STAR_3);
//
//    public static final Menu MENU_STAR_1D = new Menu(MENU_ID + 5, "Star_meal_lunch", new Date((new Date()).getTime() - 86400000), RestoranTestData.RESTORAN_STAR);
//    public static final Menu MENU_PEARL_1D = new Menu(MENU_ID + 6, "Pearl_meal_lunch", new Date((new Date()).getTime() - 86400000), RestoranTestData.RESTORAN_PEARL);
//    public static final Menu MENU_STAR_1_1D = new Menu(MENU_ID + 7, "Star_1_meal_lunch", new Date((new Date()).getTime() - 86400000), RestoranTestData.RESTORAN_STAR_1);
//    public static final Menu MENU_STAR_2_1D = new Menu(MENU_ID + 8, "Star_2_meal_lunch", new Date((new Date()).getTime() - 86400000), RestoranTestData.RESTORAN_STAR_2);
//    public static final Menu MENU_STAR_3_1D = new Menu(MENU_ID + 9, "Star_3_meal_lunch", new Date((new Date()).getTime() - 86400000), RestoranTestData.RESTORAN_STAR_3);
//
//    public static final Menu MENU_STAR_2D = new Menu(MENU_ID +10, "Star_meal_breakfast", new Date((new Date()).getTime() - 172800000), RestoranTestData.RESTORAN_STAR);
//    public static final Menu MENU_PEARL_2D = new Menu(MENU_ID + 11, "Pearl_meal_breakfast", new Date((new Date()).getTime() - 172800000), RestoranTestData.RESTORAN_PEARL);
//    public static final Menu MENU_STAR_1_2D = new Menu(MENU_ID + 12, "Star_1_meal_breakfast", new Date((new Date()).getTime() - 172800000), RestoranTestData.RESTORAN_STAR_1);
//    public static final Menu MENU_STAR_2_2D = new Menu(MENU_ID + 13, "Star_2_meal_breakfast", new Date((new Date()).getTime() - 172800000), RestoranTestData.RESTORAN_STAR_2);
//    public static final Menu MENU_STAR_3_2D = new Menu(MENU_ID + 14, "Star_3_meal_breakfast", new Date((new Date()).getTime() - 172800000), RestoranTestData.RESTORAN_STAR_3);
//
//    public static final List<Menu> menuList = List.of(MENU_STAR, MENU_PEARL, MENU_STAR_1, MENU_STAR_2, MENU_STAR_3,
//            MENU_STAR_1D, MENU_PEARL_1D, MENU_STAR_1_1D, MENU_STAR_2_1D, MENU_STAR_3_1D,
//            MENU_STAR_2D, MENU_PEARL_2D, MENU_STAR_1_2D, MENU_STAR_2_2D, MENU_STAR_3_2D);

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime", "restoran");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dateTime", "restoran").isEqualTo(expected);
    }
}
