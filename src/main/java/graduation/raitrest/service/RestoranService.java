package graduation.raitrest.service;

import graduation.raitrest.model.entities.Restoran;
import graduation.raitrest.repository.datajpa.RestoranRepository;
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

    public List<Restoran> getAll() {
        return restoranRepository.getAll();
    }

    public List<Restoran> getAll(int user_id) {
        return restoranRepository.getAll(user_id);
    }

    public Restoran get(int id) {
        return checkNotFoundWithId(restoranRepository.get(id), id);
    }

    public Restoran get(int id, int userId) {
        return checkNotFoundWithId(restoranRepository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(restoranRepository.delete(id, userId), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(restoranRepository.delete(id), id);
    }

    public void update(Restoran restoran, int userId) {
        Assert.notNull(restoran, "meal must not be null");
        checkNotFoundWithId(restoranRepository.save(restoran, userId), restoran.getId());
    }

}
