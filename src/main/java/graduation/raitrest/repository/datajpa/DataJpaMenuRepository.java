package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.Menu;
import graduation.raitrest.model.entities.Restoran;
import graduation.raitrest.repository.MenuRepository;
import graduation.raitrest.repository.datajpa.grud.CrudMenuRepository;
import graduation.raitrest.repository.datajpa.grud.CrudRestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {
    private final CrudRestoranRepository crudRestoranRepository;
    private CrudMenuRepository crudMenuRepository;


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
//        Restoran restoran = crudRestoranRepository.getWithUser( menu.getRestoran().getId());
//        if (restoran == null || restoran.getUser().getId() != userId) return null;

//        Restoran restoran = crudRestoranRepository.getOne(menu.getRestoran().getId());
//        if (restoran == null) {
//            return  null;
//        }
        return crudMenuRepository.save(menu);
    }

    @Override
    public Menu save(Menu menu, int restoranID, int userId) {
        if (!menu.isNew() && get(menu.getId(), userId) == null) {
            return null;
        }
//        Restoran rest = crudRestoranRepository.getWithUser(restoranID);
//        if (rest == null || rest.getUser().getId() != userId) {
//            return null;
//        }
//        menu.setRestoran(rest);
        Restoran restoran = crudRestoranRepository.getOne(restoranID);
        if (restoran == null) {
            return  null;
        }
        menu.setRestoran(restoran);
        return crudMenuRepository.save(menu);
    }


    @Override
    public boolean delete(int id) {
        return crudMenuRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudMenuRepository.delete(id, userId) != 0;
    }

    @Override
    public Menu get(int id) {
        return crudMenuRepository.findById(id).orElse(null);
    }

    @Override
    public Menu get(int id, int userId) {
//        Menu menu = crudMenuRepository.getWithRestoran(id);
//        if (menu.getRestoran().getUser().getId() == userId)
//            return menu;
//        return null;
      return   crudMenuRepository.getWithRestoran(id, userId);
    }

    @Override
    public List<Menu> getAll() {
        return crudMenuRepository.findAll();
    }

    @Override
    public List<Menu> getAll(int userId) {
        return crudMenuRepository.findAll(userId);
    }
}
