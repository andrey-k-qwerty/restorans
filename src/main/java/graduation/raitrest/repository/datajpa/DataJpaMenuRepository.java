package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.Menu;
import graduation.raitrest.repository.MenuRepository;
import graduation.raitrest.repository.datajpa.grud.CrudMenuRepository;
import graduation.raitrest.repository.datajpa.grud.CrudRestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.GregorianCalendar;
import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {
    private CrudMenuRepository crudMenuRepository;
    private final CrudRestoranRepository crudRestoranRepository;

    @Autowired
    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestoranRepository crudRestoranRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestoranRepository = crudRestoranRepository;
    }

    @Override
    @Transactional
    public Menu save(Menu menu, int userId) {
        if (!menu.isNew() && get(menu.getId(), userId) == null) {
            return null;
        }
        menu.setRestoran(crudRestoranRepository.getWithUser(userId));
        return crudMenuRepository.save(menu);
    }


    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Menu get(int id) {
        return crudMenuRepository.findById(id).orElse(null);
    }

    @Override
    public Menu get(int id, int userId) {
//        return crudMenuRepository.findById(id).filter(menu -> menu.getRestoran().getUser().getId() == userId).orElse(null);
        Menu menu = crudMenuRepository.getWithRestoran(id);
        if (menu.getRestoran().getUser().getId() == userId)
            return menu;
        return null;
    }

    @Override
    public List<Menu> getAll() {
        return null;
    }

    @Override
    public List<Menu> getAll(int userId) {
        return null;
    }
}
