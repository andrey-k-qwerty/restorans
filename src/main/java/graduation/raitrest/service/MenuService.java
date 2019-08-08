package graduation.raitrest.service;

import graduation.raitrest.model.entities.Menu;
import graduation.raitrest.model.entities.Restoran;
import graduation.raitrest.repository.MenuRepository;
import graduation.raitrest.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static graduation.raitrest.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {
    @Autowired
    private final MenuRepository menuRepository;

    @Autowired
    private final RestoranRepository restoranRepository;

    public MenuService(MenuRepository menuRepository, RestoranRepository restoranRepository) {
        this.menuRepository = menuRepository;
        this.restoranRepository = restoranRepository;
    }

    public Menu get(int id, int userId) {
        return checkNotFoundWithId(menuRepository.get(id, userId), id);
    }
    public Menu get(int id) {
        return checkNotFoundWithId(menuRepository.get(id), id);
    }

    public List<Menu> getAll(int userId) {
        return menuRepository.getAll(userId);
    }
    public List<Menu> getAll() {
        return menuRepository.getAll();
    }
    public void update(Menu menu, int userId) {
        Assert.notNull(menu, "menu must not be null");
        Assert.notNull(menu.getRestoran(), "menu#restaurant must not be null");
        checkNotFoundWithId(menuRepository.save(menu, userId), menu.getId());
    }

    public void update(Menu menu, int restoranId, int userId) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(menuRepository.save(menu,restoranId, userId), menu.getId());
    }

    public Menu create(Menu menu, int restoranId, int userId) {
        Assert.notNull(menu, "menu must not be null");
         return menuRepository.save(menu,restoranId, userId);
    }
    public Menu create(Menu menu,  int userId) {
        Assert.notNull(menu, "menu must not be null");
        Assert.notNull(menu.getRestoran(), "menu#restaurant must not be null");
        return menuRepository.save(menu, userId);
    }
    public void delete(int id, int userId) {
        checkNotFoundWithId(menuRepository.delete(id, userId), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(menuRepository.delete(id), id);
    }


}
