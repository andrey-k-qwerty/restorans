package graduation.raitrest.repository.datajpa;

import graduation.raitrest.model.entities.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestoranRepository extends JpaRepository<Restoran, Integer> {
    @Transactional
    @Modifying
    //    @Query(name = User.DELETE)
    @Query("DELETE FROM Restoran r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Restoran r WHERE r.id=:id AND r.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);


    @Override
    @Query("SELECT distinct  r FROM Restoran r left join FETCH r.user")
    List<Restoran> findAll();
}
