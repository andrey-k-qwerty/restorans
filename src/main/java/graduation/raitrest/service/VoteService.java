package graduation.raitrest.service;

import graduation.raitrest.model.entities.Vote;
import graduation.raitrest.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graduation.raitrest.util.ValidationUtil.checkNotFoundWithId;


@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote get(int id) {
        return checkNotFoundWithId(voteRepository.get(id), id);
    }

    public Vote get(int id, int userId) {
        return checkNotFoundWithId(voteRepository.get(id, userId), id);
    }
}
