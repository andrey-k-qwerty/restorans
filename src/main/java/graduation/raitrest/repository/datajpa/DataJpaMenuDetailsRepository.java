package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.model.entities.Restaurant;
import graduation.raitrest.repository.MenuDetailsRepository;
import graduation.raitrest.repository.datajpa.grud.CrudMenuDetailsRepository;
import graduation.raitrest.repository.datajpa.grud.CrudRestaurantRepository;
import jdk.jfr.Timestamp;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMenuDetailsRepository implements MenuDetailsRepository {
    private final CrudMenuDetailsRepository crudMenuDetailsRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMenuDetailsRepository(CrudMenuDetailsRepository crudMenuDetailsRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuDetailsRepository = crudMenuDetailsRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public MenuDetails save(MenuDetails MenuDetail, int restaurantID, int managerId) {
         if (!MenuDetail.isNew() && get(MenuDetail.getId(), managerId) == null) {
            return null;
        }
//        Restaurant rest = crudRestaurantRepository.getWithUser(restoranID);
//        if (rest == null || rest.getUser().getId() != userId) {
//            return null;
//        }
//        MenuDetail.setRestaurant(rest);
        Restaurant restaurant = crudRestaurantRepository.getOne(restaurantID);
        if (restaurant == null) {
            return  null;
        }
        MenuDetail.setRestaurant(restaurant);
        return crudMenuDetailsRepository.save(MenuDetail);
    }

    @Override
    @Transactional
    public MenuDetails save(MenuDetails MenuDetail, int managerId) {
        if (!MenuDetail.isNew() && get(MenuDetail.getId(), managerId) == null) {
            return null;
        }
        return crudMenuDetailsRepository.save(MenuDetail);
    }

    @Override
    public boolean delete(int id) {
        return crudMenuDetailsRepository.delete(id)!=0;
    }

    @Override
    public boolean delete(int id, int managerId) {
        return  crudMenuDetailsRepository.delete(id,managerId)!=0;
    }

    @Override
    public MenuDetails get(int id) {
        return crudMenuDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public MenuDetails get(int id, int managerId) {

        return crudMenuDetailsRepository.getWithManager(id, managerId);
    }

    @Override
    public List<MenuDetails> getAll() {
        return crudMenuDetailsRepository.findAll();
    }

    @Override
    public List<MenuDetails> getAllByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return crudMenuDetailsRepository.getAllByDateTime(startDateTime, endDateTime);
    }

    @Override
    public List<MenuDetails> getAllByDateTimeByRestaurantId(LocalDateTime startDateTime, LocalDateTime endDateTime, int restaurantID) {
        return crudMenuDetailsRepository.getAllByDateTimeByRestaurantId(startDateTime,endDateTime,restaurantID);
    }

    @Override
    public List<MenuDetails> getAllByDateTimeByManagerId(LocalDateTime startDateTime, LocalDateTime endDateTime, int managerID) {
        return null;
    }

    @Override
    public List<MenuDetails> getAllByDateTimeByRestaurantIdAndManagerId(LocalDateTime startDateTime, LocalDateTime endDateTime, int restaurantID, int managerId) {
        return crudMenuDetailsRepository.getAllByDateTimeByRestaurantIdAndManagerId(startDateTime,endDateTime,restaurantID,managerId);
    }


    @Override
    public List<MenuDetails> getAll(int managerId) {
     //   return crudMenuDetailsRepository.getAllWithManager(managerId);
        return crudMenuDetailsRepository.getAllByDateTimeByManagerId(  LocalDateTime.now().minusYears(100),LocalDateTime.now().plusYears(100),managerId);

    }

    @Override
    public List<MenuDetails> getAll(int restaurantID, int managerId) {
        return  crudMenuDetailsRepository.getAllByDateTimeByRestaurantIdAndManagerId( LocalDateTime.now().minusYears(100),LocalDateTime.now().plusYears(100),restaurantID,managerId);
    }


}
