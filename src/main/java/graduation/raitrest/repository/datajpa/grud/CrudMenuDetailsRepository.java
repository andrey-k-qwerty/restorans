package graduation.raitrest.repository.datajpa.grud;

import graduation.raitrest.model.entities.MenuDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuDetailsRepository extends JpaRepository<MenuDetails, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuDetails md WHERE md.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuDetails md WHERE md.id=?1 and md.restaurant.id in" +
            " (select r.id from Restaurant r  where  r.id=md.restaurant.id and r.manager.id = ?2)")
    int delete(int id, int managerId);

    @Query("SELECT md FROM MenuDetails md left join fetch md.restaurant where md.restaurant.manager.id=?2 and md.id=?1")
    MenuDetails getWithManager(int id, int managerId);

    @Query("SELECT md FROM MenuDetails md left join fetch md.restaurant where md.restaurant.manager.id=?1")
    List<MenuDetails> getAllWithManager(int managerId);

   // @Query("SELECT md FROM MenuDetails md left join fetch md.restaurant")
    List<MenuDetails> getAllByDateTimeStartingWith();

}
