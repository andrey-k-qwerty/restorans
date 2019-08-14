package graduation.raitrest;

import graduation.raitrest.model.entities.Restoran;

import java.util.List;

import static graduation.raitrest.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestoranTestData {
    public static final int RESTORAN_ID = START_SEQ + 7;
    public static final Restoran RESTORAN_STAR = new Restoran(RESTORAN_ID, "Star");
    public static final Restoran RESTORAN_PEARL = new Restoran(RESTORAN_ID + 1, "Pearl");
    public static final Restoran RESTORAN_SUPER_STAR = new Restoran(RESTORAN_ID + 2, "Super Star");
    //  У черной жемчужины два хозяина - манаджер и манажер_1
    public static final Restoran RESTORAN_BLACK_PEARL_MAN_1 = new Restoran(RESTORAN_ID + 3, "Black Pearl");
    public static final Restoran RESTORAN_BLACK_PEARL_MAN = new Restoran(RESTORAN_ID + 4, "Black Pearl");


    public static List<Restoran> RESTORAN_LIST = List.of(RESTORAN_STAR, RESTORAN_PEARL, RESTORAN_SUPER_STAR,
            RESTORAN_BLACK_PEARL_MAN_1, RESTORAN_BLACK_PEARL_MAN);

    public static Restoran getCreated() {
        return new Restoran(null, "New restaurant");
    }

    public static Restoran getUpdated() {
        return new Restoran(RESTORAN_ID, "Update Star");
    }

    public static void assertMatch(Restoran actual, Restoran expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
        //  assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Restoran> actual, Restoran... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restoran> actual, Iterable<Restoran> expected) {
        //  assertThat(actual).usingElementComparatorIgnoringFields("registered").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}
