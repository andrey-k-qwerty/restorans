package graduation.raitrest.repository.datajpa.grud;

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
    @Query("SELECT distinct  r FROM Menu r left join FETCH r.restoran")
    List<Menu> findAll();

// SELECT distinct m,  r, u FROM Menu m join Restoran r on r.id= m.restoran.id join User u on r.user.id = u.id
// select m  from Menu m left join  Restoran r on m.restoran.id  = r.id left join User u on r.id = u.id
// select m,r  from Menu m left join  Restoran r on m.restoran.id  = r.id left join fetch r.user
    @Query("SELECT distinct  r FROM Menu r left join FETCH r.restoran where r.restoran.user.id = :user_id")
//    @Query(value = "select distinct m.* \n" +
//            "from MENUS m\n" +
//            "left join RESTORANS R on m.RESTORAN_ID = R.ID\n" +
//            "left join USERS U on R.USER_ID = U.ID" , nativeQuery = true)
    List<Menu> findAll(@Param("user_id") int user_id);

//    @EntityGraph(attributePaths = {"restoran"})
//    @Query("SELECT u FROM Menu u WHERE u.id=?1")
//    Menu getWithUser(int id);
}
