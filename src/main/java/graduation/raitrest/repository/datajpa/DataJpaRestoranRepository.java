package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.Restoran;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaRestoranRepository implements RestoranRepository {
    @Autowired
    private CrudRestoranRepository crudRestoranRepository;
    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Restoran save(Restoran restoran) {
        return crudRestoranRepository.save(restoran);
    }

    @Override
    public Restoran save(Restoran restoran, int userId) {
        if (!restoran.isNew() && get(restoran.getId(), userId) == null) {
            return null;
        }
        restoran.setUser(crudUserRepository.getOne(userId));
        return crudRestoranRepository.save(restoran);
    }

    @Override
    public boolean delete(int id) {
        return crudRestoranRepository.delete(id) != 0;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRestoranRepository.delete(id,userId) !=0;
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
