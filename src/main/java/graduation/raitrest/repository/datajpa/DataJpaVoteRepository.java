package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.Vote;
import graduation.raitrest.repository.VoteRepository;
import graduation.raitrest.repository.datajpa.grud.CrudVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Override
    public Vote save(int menuId, int userId) {
        return null;
    }


    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Vote get(int id) {
        return crudVoteRepository.findById(id).orElse(null);
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id).filter(vote -> vote.getUser().getId()==userId).orElse(null);
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }

    @Override
    public List<Vote> getAll(int userId) {
        return null;
    }

    @Override
    public List<Vote> filterByDateTime(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }

    @Override
    public List<Vote> filterByDateTime(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }
}
