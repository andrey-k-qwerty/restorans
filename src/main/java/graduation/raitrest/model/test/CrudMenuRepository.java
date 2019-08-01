package graduation.raitrest.model.test;

import graduation.raitrest.model.entities.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    @Transactional
    @Modifying
    //    @Query(name = User.DELETE)
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restoran.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);


    @Override
    @Query("SELECT distinct  r FROM Menu r left join FETCH r.restoran.user")
    List<Menu> findAll();


    @Query("SELECT distinct  r FROM Menu r left join FETCH r.restoran where r.restoran.user.id = :user_id")
    List<Menu> findAll(@Param("user_id") int user_id);

//    @EntityGraph(attributePaths = {"restoran"})
//    @Query("SELECT u FROM Menu u WHERE u.id=?1")
//    Menu getWithUser(int id);
}
