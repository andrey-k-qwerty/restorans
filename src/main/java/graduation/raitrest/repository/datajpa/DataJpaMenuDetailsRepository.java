package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.repository.MenuDetailsRepository;
import graduation.raitrest.repository.datajpa.grud.CrudMenuDetailsRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMenuDetailsRepository implements MenuDetailsRepository {
    private final CrudMenuDetailsRepository crudMenuDetailsRepository;

    public DataJpaMenuDetailsRepository(CrudMenuDetailsRepository crudMenuDetailsRepository) {
        this.crudMenuDetailsRepository = crudMenuDetailsRepository;
    }

    @Override
    public MenuDetails save(MenuDetails menu, int restaurantID, int managerId) {
        return null;
    }

    @Override
    public MenuDetails save(MenuDetails menu, int managerId) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(int id, int managerId) {
        return false;
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
    public List<MenuDetails> getAll(int managerId) {
        return crudMenuDetailsRepository.getAllWithManager(managerId);
    }

    @Override
    public List<MenuDetails> getAll(int restaurantID, int managerId) {
        return null;
    }


}
