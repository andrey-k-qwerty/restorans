package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.repository.MenuDetailsRepository;

import java.util.List;

public class DataJpaMenuDetailsRepositiry implements MenuDetailsRepository {
    @Override
    public MenuDetails save(MenuDetails menu, int restoranID, int managerId) {
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
        return null;
    }

    @Override
    public MenuDetails get(int id, int managerId) {
        return null;
    }

    @Override
    public List<MenuDetails> getAll() {
        return null;
    }

    @Override
    public List<MenuDetails> getAll(int managerId) {
        return null;
    }

    @Override
    public List<MenuDetails> getAll(int restoranID, int managerId) {
        return null;
    }
}
