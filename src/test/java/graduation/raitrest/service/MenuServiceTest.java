package graduation.raitrest.service;

import graduation.raitrest.model.entities.Menu;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static graduation.raitrest.MenuTestData.*;
import static graduation.raitrest.UserTestData.ADMIN_ID;
import static org.junit.Assert.*;

public class MenuServiceTest extends AbstractServiceTest {
    @Autowired
    protected MenuService service;
    @Test
    public void get() {


        Menu actual = service.get(MENU_ID,ADMIN_ID );
        assertMatch(actual, MENU_STAR);
    }

    @Test
    public void testGet() {
    }
}