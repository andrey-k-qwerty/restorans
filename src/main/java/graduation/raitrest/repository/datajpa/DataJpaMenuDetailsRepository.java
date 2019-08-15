package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.repository.MenuDetailsRepository;
import graduation.raitrest.repository.datajpa.grud.CrudMenuDetailsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaMenuDetailsRepository implements MenuDetailsRepository {
    private final CrudMenuDetailsRepository crudMenuDetailsRepository;

    public DataJpaMenuDetailsRepository(CrudMenuDetailsRepository crudMenuDetailsRepository) {
        this.crudMenuDetailsRepository = crudMenuDetailsRepository;
    }

    @Override
    public MenuDetails save(MenuDetails menu, int restoranID, int managerId) {
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
        return crudMenuDetailsRepository.get(id,managerId);
    }

    @Override
    public MenuDetails getFull(int id) {
        return crudMenuDetailsRepository.getFull(id);
    }

    @Override
    public MenuDetails getFull(int id, int managerId) {
        return crudMenuDetailsRepository.getFull(id, managerId);
    }

    @Override
    public List<MenuDetails> getAll() {
        return crudMenuDetailsRepository.findAll();
    }

    @Override
    public List<MenuDetails> getAll(int managerId) {
        return null;
        //    return crudMenuDetailsRepository.findAllByManger();
    }

    @Override
    public List<MenuDetails> getAll(int restoranID, int managerId) {
        return null;
    }


}
