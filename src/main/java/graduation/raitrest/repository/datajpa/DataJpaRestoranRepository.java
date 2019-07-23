package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.Restoran;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaRestoranRepository implements RestoranRepository {
    @Autowired
    private CrudRestoranRepository crudRestoranRepository;

    @Override
    public Restoran save(Restoran restoran) {
        return crudRestoranRepository.save(restoran);
    }

    @Override
    public Restoran save(Restoran restoran, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return crudRestoranRepository.delete(id) != 0 ;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Restoran get(int id) {
        return crudRestoranRepository.findById(id).orElse(null);
    }

    @Override
    public Restoran get(int id, int userId) {
        return null;
    }

    @Override
    public List<Restoran> getAll() {
        return null;
    }

    @Override
    public List<Restoran> getAll(int userId) {
        return null;
    }
}
