package graduation.raitrest;

import graduation.raitrest.model.entities.Restoran;

import java.util.List;

import static graduation.raitrest.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestoranTestData {
    public static final int RESTORAN_ID = START_SEQ + 3;
    public static final Restoran RESTORAN1 = new Restoran(RESTORAN_ID,"Star");
    public static final Restoran RESTORAN2 = new Restoran(RESTORAN_ID + 1,"Pearl");

    public static  List<Restoran> RESTORAN_LIST = List.of(RESTORAN1,RESTORAN2);

    public static void assertMatch(Restoran actual, Restoran expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
     //   assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Restoran> actual, Restoran... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restoran> actual, Iterable<Restoran> expected) {
      //  assertThat(actual).usingElementComparatorIgnoringFields("registered").isEqualTo(expected);
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}
