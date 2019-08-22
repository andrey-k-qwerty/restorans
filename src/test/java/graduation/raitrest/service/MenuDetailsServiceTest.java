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
        // по айди ресторана
        MenuDetails newMenu = new MenuDetails(RESTAURANT_STAR,
                "Пятое блюдо", "Хлеб", "1 кусочек", new BigDecimal("0.10"), LocalDateTime.now());

        MenuDetails created = service.create(newMenu, RestoranTestData.RESTAURANT_ID, MANAGER_ID);
        newMenu.setId(created.getId());
        newMenu.setRestaurant(created.getRestaurant());
        assertMatch(created, newMenu);
        MenuDetails menuDetailsGet = service.get(created.getId());
        assertMatch(menuDetailsGet, newMenu);
//        assertMatch(service.getAll(MANAGER_ID),MENU_STAR,MENU_STAR_1D,MENU_STAR_2D,newMenu);
//
//        // по объекту ресторана
//        Menu newMenu2 = new Menu("New menu 2", new Date());
//        newMenu2.setRestaurant(RestoranTestData.RESTAURANT_STAR);
//        Menu created2 = service.create(newMenu2,  MANAGER_ID);
//        newMenu2.setId(created2.getId());
//
//        assertThat(newMenu2).isEqualToIgnoringGivenFields(created2, "dateTime");
//        assertMatch(service.getAll(MANAGER_ID),MENU_STAR,MENU_STAR_1D,MENU_STAR_2D,newMenu,newMenu2);


    }
//
    @Test
    public void update()  {
        MenuDetails updated =  new MenuDetails(MENU_DETAILS_ID + 4, RESTAURANT_PEARL,
                "Первое блюдо", "Супер Уха", "100 грамм", new BigDecimal("25.00"),
                LocalDateTime.now());
       // updated.setRestaurant(RestoranTestData.RESTAURANT_PEARL);
        service.update(updated, MANAGER_ID);
        assertMatch(service.get(MENU_DETAILS_ID + 4, MANAGER_ID), updated);

//        updated = new Menu(MENU_ID + 1,"New super super menu", new Date());
//        service.update(updated,RestoranTestData.RESTAURANT_ID + 1, ADMIN_ID);
//        assertMatch(service.get(MENU_ID + 1, ADMIN_ID), updated);

    }
//    @Test
//    public void updateNotFound() throws Exception {
//        thrown.expect(NotFoundException.class);
//        thrown.expectMessage("Not found entity with id=" + MENU_ID);
//        service.update(MENU_STAR, ADMIN_ID);
//    }
//

}