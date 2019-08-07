package graduation.raitrest;

import graduation.raitrest.model.entities.Menu;

import java.util.Date;
import java.util.List;

import static graduation.raitrest.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {
    public static final int MENU_ID = START_SEQ + 20;

    public static final Menu MENU_STAR = new Menu(MENU_ID,"Star_meal_dinner",new Date(),RestoranTestData.RESTORAN_STAR);
    public static final Menu MENU_PEARL = new Menu(MENU_ID+1,"Pearl_meal_dinner",new Date(),RestoranTestData.RESTORAN_PEARL);
    public static final Menu MENU_STAR_1 = new Menu(MENU_ID+2,"Star_1_meal_dinner",new Date(),RestoranTestData.RESTORAN_STAR_1);
    public static final Menu MENU_STAR_2 = new Menu(MENU_ID+3,"Star_2_meal_dinner",new Date(),RestoranTestData.RESTORAN_STAR_2);
    public static final Menu MENU_STAR_3 = new Menu(MENU_ID+4,"Star_3_meal_dinner",new Date(),RestoranTestData.RESTORAN_STAR_3);

    public static final List<Menu>  menuList = List.of(MENU_STAR,MENU_PEARL,MENU_STAR_1,MENU_STAR_2,MENU_STAR_3);

     public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime","restoran");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dateTime","restoran").isEqualTo(expected);
    }
}
