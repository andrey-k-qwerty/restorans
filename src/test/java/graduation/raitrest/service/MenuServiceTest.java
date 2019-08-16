package graduation.raitrest.service;

import graduation.raitrest.RestoranTestData;
import graduation.raitrest.UserTestData;
import graduation.raitrest.model.entities.Menu;
import graduation.raitrest.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static graduation.raitrest.MenuTestData.*;
import static graduation.raitrest.UserTestData.ADMIN_ID;
import static graduation.raitrest.UserTestData.MANAGER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MenuServiceTest extends AbstractServiceTest {
    @Autowired
    protected MenuService service;

//    @Test
//    public void getWithRestaurant() {
//        Menu actual = service.get(MENU_ID,MANAGER_ID );
//        assertMatch(actual, MENU_STAR);
//        RestoranTestData.assertMatch(actual.getRestaurant(),RestoranTestData.RESTAURANT_STAR);
//    }
//
//    @Test
//    public void getByIdWithoutRestaurant() {
//        Menu menu = service.get(MENU_ID + 1);
//        assertMatch(menu,MENU_PEARL);
//    }
//
//    @Test
//    public void getByIdAndUserId() {
//        Menu menu = service.get(MENU_ID , MANAGER_ID);
//        assertMatch(menu,MENU_STAR);
//    }
//
//    @Test
//   public  void getNotFound() throws Exception {
//        thrown.expect(NotFoundException.class);
//        service.get(1, ADMIN_ID);
//    }
//    @Test
//    public void getNotOwn() throws Exception {
//        thrown.expect(NotFoundException.class);
//        service.get(MENU_ID, ADMIN_ID);
//    }
//
//
//    @Test
//    public void getAll() {
//        List<Menu> menuAll = service.getAll();
//        assertMatch(menuAll, menuList);
//
//    }
//
//    @Test
//    public void getAllByUserID() {
//        List<Menu> menuAllManager = service.getAll(UserTestData.MANAGER_ID);
//        assertMatch(menuAllManager, MENU_STAR,MENU_STAR_1D,MENU_STAR_2D);
//    }
//
//    @Test
//    public void create()   {
//        // по айди ресторана
//        Menu newMenu = new Menu("New menu", new Date());
//        Menu created = service.create(newMenu, RestoranTestData.RESTAURANT_ID, MANAGER_ID);
//        newMenu.setId(created.getId());
//        newMenu.setRestaurant(created.getRestaurant());
//        assertThat(newMenu).isEqualToIgnoringGivenFields(created, "dateTime");
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
//
//
//    }
//
//    @Test
//    public void update()  {
//        Menu updated = new Menu(MENU_ID + 1,"New super menu", new Date());
//        updated.setRestaurant(RestoranTestData.RESTAURANT_PEARL);
//        service.update(updated, ADMIN_ID);
//        assertMatch(service.get(MENU_ID + 1, ADMIN_ID), updated);
//
//        updated = new Menu(MENU_ID + 1,"New super super menu", new Date());
//        service.update(updated,RestoranTestData.RESTAURANT_ID + 1, ADMIN_ID);
//        assertMatch(service.get(MENU_ID + 1, ADMIN_ID), updated);
//
//    }
//    @Test
//    public void updateNotFound() throws Exception {
//        thrown.expect(NotFoundException.class);
//        thrown.expectMessage("Not found entity with id=" + MENU_ID);
//        service.update(MENU_STAR, ADMIN_ID);
//    }
//
//    @Test
//    public void delete() {
//        service.delete(MENU_ID);
//        List<Menu> menuAllManager = service.getAll(UserTestData.MANAGER_ID);
//        assertMatch(menuAllManager, MENU_STAR_1D,MENU_STAR_2D);
//    }
//
//    @Test
//    public void deleteWithUserID() {
//        service.delete(MENU_ID+1,ADMIN_ID);
//        assertMatch(service.getAll(ADMIN_ID), MENU_PEARL_1D,MENU_PEARL_2D);
//    }
//
//    @Test
//    public void deleteNotFound()  {
//        thrown.expect(NotFoundException.class);
//        service.delete(1, ADMIN_ID);
//    }
//
//    @Test
//    public void deleteNotOwn() {
//        thrown.expect(NotFoundException.class);
//        service.delete(MENU_ID, ADMIN_ID);
//    }


}