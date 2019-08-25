package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.Menu;
import graduation.raitrest.model.entities.Restaurant;
import graduation.raitrest.repository.MenuRepository;
import graduation.raitrest.repository.datajpa.grud.CrudMenuRepository;
import graduation.raitrest.repository.datajpa.grud.CrudRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {
    private final CrudRestaurantRepository crudRestaurantRepository;
    private CrudMenuRepository crudMenuRepository;


    @Autowired
    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuRepository = crudMenuRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Menu save(Menu menu, int userId) {
        if (!menu.isNew() && get(menu.getId(), userId) == null ) {
            return null;
        }

        return crudMenuRepository.save(menu);
    }

    @Override
    public Menu save(Menu menu, int restaurantID, int userId) {
        if (!menu.isNew() && get(menu.getId(), userId) == null) {
            return null;
        }
//        Restaurant rest = crudRestaurantRepository.getWithUser(restoranID);
//        if (rest == null || rest.getUser().getId() != userId) {
//            return null;
//        }
//        menu.setRestaurant(rest);
        Restaurant restaurant = crudRestaurantRepository.getOne(restaurantID);
        if (restaurant == null) {
            return  null;
        }
        menu.setRestaurant(restaurant);
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
//        if (menu.getRestaurant().getUser().getId() == userId)
//            return menu;
//        return null;

//      return   crudMenuRepository.getWithRestoran(id, userId);
        return null;
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
