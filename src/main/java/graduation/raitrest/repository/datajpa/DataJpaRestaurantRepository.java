package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.Restaurant;
import graduation.raitrest.model.entities.User;
import graduation.raitrest.repository.RestoranRepository;
import graduation.raitrest.repository.datajpa.grud.CrudRestoranRepository;
import graduation.raitrest.repository.datajpa.grud.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class DataJpaRestaurantRepository implements RestoranRepository {

    @Autowired
    private CrudRestoranRepository crudRestoranRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

//    @Override
//    public Restaurant save(Restaurant restaurant) {
//        return crudRestoranRepository.save(restaurant);
//    }

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        if (!restaurant.isNew() && get(restaurant.getId(), userId) == null) {
            return null;
        }
        restaurant.setManager(crudUserRepository.getOne(userId));
        return crudRestoranRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRestoranRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRestoranRepository.delete(id, userId) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestoranRepository.findById(id).orElse(null);
    }

    @Override
    public Restaurant get(int id, int userId) {

        return crudRestoranRepository.findById(id).filter(r -> r.getManager().getId() ==  userId).orElse(null);
    }

    @Override
    public Restaurant getWithUser(int id) {
        return crudRestoranRepository.getWithUser(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestoranRepository.findAll();
    }

    @Override
    public List<Restaurant> getAll(int userId) {
        return crudRestoranRepository.findAll(userId);
    }
}
