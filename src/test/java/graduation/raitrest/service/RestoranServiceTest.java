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
        assertMatch(all, RESTORAN1);
        all = service.getAll(MANAGER_ID);
        assertMatch(all, RESTORAN2);
    }

    @Test
    public void get() {
        Restoran restoran = service.get(RESTORAN_ID);
        assertMatch(restoran, RESTORAN1);
    }

    @Test
    public void getByUserId() {
        Restoran restoran = service.get(RESTORAN_ID + 1, MANAGER_ID);
        assertMatch(restoran, RESTORAN2);
    }

    @Test
    public void delete() {
        service.delete(RESTORAN_ID);
        assertMatch(service.getAll(), RESTORAN2);
    }

    @Test
    public void deleteWithUserID() {
        service.delete(RESTORAN_ID+1,MANAGER_ID);
        assertMatch(service.getAll(), RESTORAN1);
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
        Restoran updated = new Restoran(RESTORAN_ID + 1,"XXX");
        service.update(updated, MANAGER_ID);
        assertMatch(service.get(RESTORAN_ID + 1, MANAGER_ID), updated);
    }

    @Test
    public void updateNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + RESTORAN_ID);
        service.update(RESTORAN1, MANAGER_ID);
    }
}