package graduation.raitrest.service;

import graduation.raitrest.model.entities.MenuDetails;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static graduation.raitrest.MenuDetailsTestData.*;
import static graduation.raitrest.UserTestData.*;

public class MenuDetailsServiceTest extends AbstractServiceTest {

    @Autowired
    protected MenuDetailsService service;


    @Test
    public void get() {
        // просто по  id
        MenuDetails menuDetails = service.get(MENU_DETAILS_ID + 2);
        assertMatch(menuDetails, MENU_DETAILS_STAR_TODAY_3);
        //---
        menuDetails = service.get(MENU_DETAILS_ID + 1);
        assertMatch(menuDetails, MENU_DETAILS_STAR_TODAY_2);
        menuDetails = service.get(MENU_DETAILS_ID);
        assertMatch(menuDetails, MENU_DETAILS_STAR_TODAY_1);
        menuDetails = service.get(MENU_DETAILS_ID + 3);
        assertMatch(menuDetails, MENU_DETAILS_STAR_TODAY_4);


        menuDetails = service.get(MENU_DETAILS_ID + 4);
        assertMatch(menuDetails, MENU_DETAILS_PEARL_TODAY_1);
        menuDetails = service.get(MENU_DETAILS_ID + 5);
        assertMatch(menuDetails, MENU_DETAILS_PEARL_TODAY_2);
        menuDetails = service.get(MENU_DETAILS_ID + 6);
        assertMatch(menuDetails, MENU_DETAILS_PEARL_TODAY_3);
        menuDetails = service.get(MENU_DETAILS_ID + 7);
        assertMatch(menuDetails, MENU_DETAILS_PEARL_TODAY_4);

        menuDetails = service.get(MENU_DETAILS_ID + 8);
        assertMatch(menuDetails, MENU_DETAILS_SUPER_STAR_TODAY_1);
        menuDetails = service.get(MENU_DETAILS_ID + 9);
        assertMatch(menuDetails, MENU_DETAILS_SUPER_STAR_TODAY_2);
        menuDetails = service.get(MENU_DETAILS_ID + 10);
        assertMatch(menuDetails, MENU_DETAILS_SUPER_STAR_TODAY_3);
        menuDetails = service.get(MENU_DETAILS_ID + 11);
        assertMatch(menuDetails, MENU_DETAILS_SUPER_STAR_TODAY_4);


        // по id и manager_id
        menuDetails = service.get(MENU_DETAILS_ID, MANAGER_ID);
        assertMatch(menuDetails, MENU_DETAILS_STAR_TODAY_1);
    }

    @Test
    public void getAll() {
        //all
        List<MenuDetails> allMenu = service.getAll();
        assertMatch(MENU_DETAILS_LIST, allMenu);

        // all by manager_id
        allMenu = service.getAll(MANAGER_1_ID);
        assertMatch(List.of(MENU_DETAILS_PEARL_TODAY_1, MENU_DETAILS_PEARL_TODAY_2, MENU_DETAILS_PEARL_TODAY_3, MENU_DETAILS_PEARL_TODAY_4, MENU_DETAILS_PEARL_YESTERDAY_1, MENU_DETAILS_PEARL_YESTERDAY_2, MENU_DETAILS_PEARL_YESTERDAY_3), allMenu);

    }

}