package graduation.raitrest.service;

import graduation.raitrest.model.entities.Restaurant;
import graduation.raitrest.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static graduation.raitrest.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestoranService {

    @Autowired
    private final RestoranRepository restoranRepository;

    public RestoranService(RestoranRepository restoranRepository) {
        this.restoranRepository = restoranRepository;
    }

    public List<Restaurant> getAll() {
        return restoranRepository.getAll();
    }

    public List<Restaurant> getAll(int user_id) {
        return restoranRepository.getAll(user_id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(restoranRepository.get(id), id);
    }

    public Restaurant get(int id, int userId) {
        return checkNotFoundWithId(restoranRepository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(restoranRepository.delete(id, userId), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(restoranRepository.delete(id), id);
    }

    public void update(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restoranRepository.save(restaurant, userId), restaurant.getId());
    }

    public Restaurant create(Restaurant restaurant, int userId) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restoranRepository.save(restaurant, userId);
    }


}
