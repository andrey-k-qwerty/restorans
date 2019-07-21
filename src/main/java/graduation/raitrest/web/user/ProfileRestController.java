package graduation.raitrest.web.user;

import graduation.raitrest.model.entities.User;
import org.springframework.stereotype.Controller;

import static graduation.raitrest.web.SecurityUtil.authUserId;


@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}