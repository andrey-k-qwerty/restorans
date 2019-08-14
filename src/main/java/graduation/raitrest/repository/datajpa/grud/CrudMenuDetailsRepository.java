package graduation.raitrest.repository.datajpa.grud;

import graduation.raitrest.model.entities.MenuDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudMenuDetailsRepository extends JpaRepository<MenuDetails, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuDetails md WHERE md.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuDetails md WHERE md.id=?1 and md.restoran.id in" +
            " (select r.id from Restoran r  where  r.id=md.restoran.id and r.user.id = ?2)")
    int delete( int id,int managerId);

    @EntityGraph(attributePaths = {"restoran","manager"})
    @Query("SELECT u FROM User u  WHERE u.id=?1")
    MenuDetails getFull(int id);

}
