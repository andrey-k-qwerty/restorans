package graduation.raitrest.service;

import graduation.raitrest.model.entities.Restoran;
import graduation.raitrest.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static graduation.raitrest.RestoranTestData.*;
import static graduation.raitrest.UserTestData.ADMIN_ID;
import static graduation.raitrest.UserTestData.MANAGER_ID;

public class RestoranServiceTest extends AbstractServiceTest {
    @Autowired
    protected RestoranService service;

    @Test
    public void getAll() {
        List<Restoran> all = service.getAll();
        assertMatch(all, RESTORAN_LIST);
    }

    @Test
    public void getAllByUserID() {
        List<Restoran> all = service.getAll(ADMIN_ID);
        // admin have 2 restaurant
        assertMatch(all, RESTORAN_STAR, RESTORAN_SUPER_STAR);
        all = service.getAll(MANAGER_ID);
        assertMatch(all, RESTORAN_PEARL,RESTORAN_BLACK_PEARL_MAN);
    }

    @Test
    public void create() throws Exception {
        Restoran newRestaurant = getCreated();
        Restoran created = service.create(newRestaurant, MANAGER_ID);
        newRestaurant.setId(created.getId());

        assertMatch(newRestaurant, created);
        assertMatch(service.getAll(MANAGER_ID), RESTORAN_PEARL, RESTORAN_BLACK_PEARL_MAN,newRestaurant);
    }

    @Test
    public void get() {
        Restoran restoran = service.get(RESTORAN_ID);
        assertMatch(restoran, RESTORAN_STAR);
    }

    @Test
    public void getByUserId() {
        Restoran restoran = service.get(RESTORAN_ID + 1, MANAGER_ID);
        assertMatch(restoran, RESTORAN_PEARL);
    }

    @Test
    public void delete() {
        service.delete(RESTORAN_ID);
        assertMatch(service.getAll(),RESTORAN_PEARL, RESTORAN_SUPER_STAR, RESTORAN_BLACK_PEARL_MAN_1,RESTORAN_BLACK_PEARL_MAN);
    }

    @Test
    public void deleteWithUserID() {
        service.delete(RESTORAN_ID,ADMIN_ID);
        assertMatch(service.getAll(), RESTORAN_PEARL, RESTORAN_SUPER_STAR, RESTORAN_BLACK_PEARL_MAN_1,RESTORAN_BLACK_PEARL_MAN);
    }

    @Test
    public void deleteNotFound()  {
        thrown.expect(NotFoundException.class);
        service.delete(1, ADMIN_ID);
    }

    @Test
    public void deleteNotOwn() {
        thrown.expect(NotFoundException.class);
        service.delete(RESTORAN_ID, MANAGER_ID);
    }

    @Test
    public void update() {
        Restoran updated = new Restoran(RESTORAN_ID ,"XXX");
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(RESTORAN_ID, ADMIN_ID), updated);
    }

    @Test
    public void updateNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + RESTORAN_ID);
        service.update(RESTORAN_STAR, MANAGER_ID);
    }
}