package graduation.raitrest.service;

import graduation.raitrest.model.entities.Restaurant;
import graduation.raitrest.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static graduation.raitrest.RestoranTestData.*;
import static graduation.raitrest.UserTestData.ADMIN_ID;
import static graduation.raitrest.UserTestData.MANAGER_ID;

public class RestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    protected RestoranService service;

    @Test
    public void getAll() {
        List<Restaurant> all = service.getAll();
        assertMatch(all, restaurantList);
    }

    @Test
    public void getAllByUserID() {
        List<Restaurant> all = service.getAll(ADMIN_ID);
        // admin have 2 restaurant
        assertMatch(all, RESTAURANT_STAR, RESTAURANT_SUPER_STAR);
        all = service.getAll(MANAGER_ID);
        assertMatch(all, RESTAURANT_PEARL, RESTAURANT_BLACK_PEARL_MAN);
    }

    @Test
    public void create() throws Exception {
        Restaurant newRestaurant = getCreated();
        Restaurant created = service.create(newRestaurant, MANAGER_ID);
        newRestaurant.setId(created.getId());

        assertMatch(newRestaurant, created);
        assertMatch(service.getAll(MANAGER_ID), RESTAURANT_PEARL, RESTAURANT_BLACK_PEARL_MAN,newRestaurant);
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(RESTAURANT_ID);
        assertMatch(restaurant, RESTAURANT_STAR);
    }

    @Test
    public void getByUserId() {
        Restaurant restaurant = service.get(RESTAURANT_ID + 1, MANAGER_ID);
        assertMatch(restaurant, RESTAURANT_PEARL);
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT_ID);
        assertMatch(service.getAll(), RESTAURANT_PEARL, RESTAURANT_SUPER_STAR,  RESTAURANT_BLACK_PEARL_MAN);
    }

    @Test
    public void deleteWithUserID() {
        service.delete(RESTAURANT_ID,ADMIN_ID);
        assertMatch(service.getAll(), RESTAURANT_PEARL, RESTAURANT_SUPER_STAR,  RESTAURANT_BLACK_PEARL_MAN);
    }

    @Test
    public void deleteNotFound()  {
        thrown.expect(NotFoundException.class);
        service.delete(1, ADMIN_ID);
    }

    @Test
    public void deleteNotOwn() {
        thrown.expect(NotFoundException.class);
        service.delete(RESTAURANT_ID, MANAGER_ID);
    }

    @Test
    public void update() {
        Restaurant updated = getCreated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(RESTAURANT_ID, ADMIN_ID), updated);
    }

    @Test
    public void updateNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + RESTAURANT_ID);
        service.update(RESTAURANT_STAR, MANAGER_ID);
    }
}