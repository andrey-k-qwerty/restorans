package graduation.raitrest.service;

import graduation.raitrest.model.entities.Menu;
import graduation.raitrest.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graduation.raitrest.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    @Autowired
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu get(int id, int userId) {
        return checkNotFoundWithId(menuRepository.get(id, userId), id);
    }
    public Menu get(int id) {
        return checkNotFoundWithId(menuRepository.get(id), id);
    }


}
