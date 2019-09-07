package graduation.raitrest.web.menu;

import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.model.to.MenuDetailTo;
import graduation.raitrest.web.SecurityUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static graduation.raitrest.util.Util.menuDetail_2_MenuDetailTo;

@RestController
@RequestMapping(value = ProfileMenuDetailRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileMenuDetailRestController extends AbstractMenuDetailController {
    public static final String REST_URL = "/rest/profile/menu";

    @Override
    @GetMapping("/all")
    public List<MenuDetailTo> getAll() {
        return super.getAll();
    }


    @GetMapping()
    public List<MenuDetailTo> getAllCurrentDayTo() {
        int userId = SecurityUtil.authUserId();
        // надо проверить только что пользователь зарегистрирован
        if (userId == 0) {
            log.info("User not register.");
            // тут ексепшин
            return null;
        }
        log.info("get menu today {} for user {}",  userId);

        return  menuDetail_2_MenuDetailTo(service.getFilterByDate(LocalDate.now(),LocalDate.now()));
    }

}
