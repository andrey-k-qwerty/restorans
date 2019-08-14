package graduation.raitrest.service;

import graduation.raitrest.model.entities.MenuDetails;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static graduation.raitrest.MenuDetailsTestData.*;

public class MenuDetailsServiceTest extends AbstractServiceTest {

    @Autowired
    protected MenuDetailsService service;

    @Test
    public void get() {
        // просто по  id, first
        MenuDetails menuDetails = service.get(MENU_DETAILS_ID);
        assertMatch(menuDetails, MENU_DETAILS_STAR_TODAY_1);
        // last
        menuDetails = service.get(MENU_DETAILS_ID + 29);
        assertMatch(menuDetails, MENU_DETAILS_SUPER_STAR_BEFORE_YESTERDAY_2);
        // middle
        menuDetails = service.get(MENU_DETAILS_ID + 17);
        assertMatch(menuDetails, MENU_DETAILS_BLACK_PEARL_YESTERDAY_2);
        // with restaurant and manager
        menuDetails = service.getFull(MENU_DETAILS_ID);
        assertMatch(menuDetails, MENU_DETAILS_STAR_TODAY_1);

    }
}