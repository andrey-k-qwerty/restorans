package graduation.raitrest.web.user;

import graduation.raitrest.model.entities.User;
import graduation.raitrest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static graduation.raitrest.util.ValidationUtil.assureIdConsistent;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected UserService service;

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }
}