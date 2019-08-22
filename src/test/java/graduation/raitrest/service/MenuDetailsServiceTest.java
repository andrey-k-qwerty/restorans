package graduation.raitrest.service;

import graduation.raitrest.RestoranTestData;
import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static graduation.raitrest.MenuDetailsTestData.*;
import static graduation.raitrest.RestoranTestData.*;
import static graduation.raitrest.UserTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
        menuDetails = service.get(MENU_DETAILS_ID + 3, MANAGER_ID);
        assertMatch(menuDetails, MENU_DETAILS_STAR_TODAY_4);
    }

    @Test
    public void getAll() {
        //all
        List<MenuDetails> allMenu = service.getAll();
        assertMatch(MENU_DETAILS_LIST, allMenu);


        // all by manager_id
        allMenu = service.getAll(MANAGER_1_ID);
        assertMatch(List.of(MENU_DETAILS_PEARL_TODAY_1, MENU_DETAILS_PEARL_TODAY_2, MENU_DETAILS_PEARL_TODAY_3, MENU_DETAILS_PEARL_TODAY_4, MENU_DETAILS_PEARL_YESTERDAY_1, MENU_DETAILS_PEARL_YESTERDAY_2, MENU_DETAILS_PEARL_YESTERDAY_3), allMenu);

        // all by date
        List<MenuDetails> menuDetailsToday = List.of(MENU_DETAILS_STAR_TODAY_1,
                MENU_DETAILS_STAR_TODAY_2, MENU_DETAILS_STAR_TODAY_3, MENU_DETAILS_STAR_TODAY_4,

                MENU_DETAILS_PEARL_TODAY_1, MENU_DETAILS_PEARL_TODAY_2, MENU_DETAILS_PEARL_TODAY_3, MENU_DETAILS_PEARL_TODAY_4,

                MENU_DETAILS_SUPER_STAR_TODAY_1, MENU_DETAILS_SUPER_STAR_TODAY_2, MENU_DETAILS_SUPER_STAR_TODAY_3, MENU_DETAILS_SUPER_STAR_TODAY_4,

                MENU_DETAILS_BLACK_PEARL_TODAY_1, MENU_DETAILS_BLACK_PEARL_TODAY_2, MENU_DETAILS_BLACK_PEARL_TODAY_3, MENU_DETAILS_BLACK_PEARL_TODAY_4);

        allMenu = service.getFilterByDate(LocalDate.now(), LocalDate.now().plusDays(1L));
        assertMatch(menuDetailsToday, allMenu);

        allMenu = service.getFilterByDateByRestaurant(LocalDate.now(), LocalDate.now().plusDays(1L),RESTAURANT_ID);
        assertMatch(List.of(MENU_DETAILS_STAR_TODAY_1,
                MENU_DETAILS_STAR_TODAY_2, MENU_DETAILS_STAR_TODAY_3, MENU_DETAILS_STAR_TODAY_4), allMenu);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(1, MANAGER_ID);
    }

    @Test
    public void getNotOwn() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MENU_DETAILS_ID, MANAGER_1_ID);
    }

    @Test
    public void create() {

        MenuDetails newMenu = new MenuDetails(RESTAURANT_STAR,
                "Пятое блюдо", "Хлеб", "1 кусочек", new BigDecimal("0.10"), LocalDateTime.now());

        MenuDetails created = service.create(newMenu, RestoranTestData.RESTAURANT_ID, MANAGER_ID);
        newMenu.setId(created.getId());
        newMenu.setRestaurant(created.getRestaurant());
        assertMatch(created, newMenu);
        MenuDetails menuDetailsGet = service.get(created.getId());
        assertMatch(menuDetailsGet, newMenu);


    }

    @Test
    public void update()  {
        MenuDetails updated =  new MenuDetails(MENU_DETAILS_ID + 3, RESTAURANT_STAR,
                "Первое блюдо", "Супер Уха", "100 грамм", new BigDecimal("25.00"),
                LocalDateTime.now());


        service.update(updated, MANAGER_ID);
        MenuDetails actual = service.get(MENU_DETAILS_ID + 3, MANAGER_ID);
        assertMatch(actual, updated);


    }
    @Test
    public void updateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + (MENU_DETAILS_ID+3));
        service.update(MENU_DETAILS_STAR_TODAY_4, ADMIN_ID);
    }


    @Test
    public void delete() {
        service.delete(MENU_DETAILS_ID);
        List<MenuDetails> menuAllManager  = service.getFilterByDateByRestaurant(LocalDate.now(), LocalDate.now().plusDays(1L),RESTAURANT_ID);
        assertMatch(menuAllManager,  MENU_DETAILS_STAR_TODAY_2, MENU_DETAILS_STAR_TODAY_3, MENU_DETAILS_STAR_TODAY_4);
    }

    @Test
    public void deleteWithUserID() {
        service.delete(MENU_DETAILS_ID+4,MANAGER_1_ID);
        List<MenuDetails> menuAllManager  = service.getFilterByDateByRestaurant(LocalDate.now(), LocalDate.now().plusDays(1L),RESTAURANT_ID +1);
        assertMatch(menuAllManager, MENU_DETAILS_PEARL_TODAY_2,MENU_DETAILS_PEARL_TODAY_3,MENU_DETAILS_PEARL_TODAY_4);
    }

    @Test
    public void deleteNotFound()  {
        thrown.expect(NotFoundException.class);
        service.delete(1, MANAGER_ID);
    }

    @Test
    public void deleteNotOwn() {
        thrown.expect(NotFoundException.class);
        service.delete(MENU_DETAILS_ID, MANAGER_1_ID);
    }


}