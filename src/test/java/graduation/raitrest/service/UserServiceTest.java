package graduation.raitrest.service;

import graduation.raitrest.model.entities.Role;
import graduation.raitrest.model.entities.User;
import graduation.raitrest.util.JpaUtil;
import graduation.raitrest.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

import static graduation.raitrest.UserTestData.*;


public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;
    @Autowired
    protected JpaUtil jpaUtil;
    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(created, newUser);

        List<User> collect = listUsers.stream().collect(Collectors.toList());
        collect.add(newUser);
        collect.sort(Comparator.comparing(User::getName));
        assertMatch(service.getAll(), collect);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        List<User> collect = listUsers.stream()
                .filter(user -> user.getId() != USER_ID)
                .sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
        assertMatch(service.getAll(), collect);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
        User admin = service.get(ADMIN_ID);
        assertMatch(admin, ADMIN);
        User manager = service.get(MANAGER_ID);
        assertMatch(manager, MANAGER);
        User user1 = service.get(MANAGER_3_ID);
        assertMatch(user1, MANAGER_3);

    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");

        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
        // меняем роль
        updated.setRoles(EnumSet.of(Role.ROLE_ADMIN));
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
        // две роли
        updated.setRoles(EnumSet.of(Role.ROLE_USER, Role.ROLE_ADMIN));
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
        // и снова одна роль
        updated.setRoles(EnumSet.of(Role.ROLE_USER));
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        List<User> collect = listUsers.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
        assertMatch(all, collect);
    }


    @Test

    public void testValidation() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", true, null, Collections.emptySet())), ConstraintViolationException.class);
      //     validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password",  true, new Date(), null)), ConstraintViolationException.class);
    }
}