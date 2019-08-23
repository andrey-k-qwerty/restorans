package graduation.raitrest.repository.datajpa.grud;

import graduation.raitrest.model.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote r WHERE r.id=:id AND r.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

}
