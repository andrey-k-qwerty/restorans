package graduation.raitrest.repository.datajpa.grud;

import graduation.raitrest.model.entities.MenuDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuDetailsRepository extends JpaRepository<MenuDetails, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuDetails md WHERE md.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuDetails md WHERE md.id=?1 and md.restoran.id in" +
            " (select r.id from Restaurant r  where  r.id=md.restoran.id and r.user.id = ?2)")
    int delete(int id, int managerId);


//    @Query("SELECT md FROM  MenuDetails md   WHERE md.id=?1 and md.manager.id in " +
//            "(select r.id from Restaurant r  where  r.id=md.restoran.id and r.user.id = ?2)")

    /*
    *  TODO Есть ошибка. Ресторан-пользователи связь многие ко многим. Нужна еще одна таблица
    *
    * */
    @Query(value = "select m.* " +
            "from MENU_DETAILS m where m.ID =?1 and m.RESTORAN_ID in (select r.ID " +
            "    from RESTORANS r where   r.USER_ID = ?2 )",nativeQuery = true)
    MenuDetails get(int id, int managerId);

    @EntityGraph(attributePaths = {"restoran", "manager"})
    @Query("SELECT md FROM  MenuDetails md   WHERE md.id=?1")
    MenuDetails getFull(int id);

    @EntityGraph(attributePaths = {"restoran", "manager"})
    @Query("SELECT md FROM  MenuDetails md   WHERE md.id=?1 and md.manager.id in " +
            "(select r.id from Restaurant r  where  r.id=md.restoran.id and r.user.id = ?2)")
    MenuDetails getFull(int id, int managerId);

    @Query("SELECT md FROM  MenuDetails md   WHERE  md.manager.id=?1")
    List<MenuDetails> findAllByManger(int managerId);

    @EntityGraph(attributePaths = {"restoran", "manager"})
    @Query("SELECT md FROM  MenuDetails md    WHERE  md.manager.id=?1")
    List<MenuDetails> findAllByMangerFull(int managerId);

//    List<MenuDetails> findAllByManagerIdAndDateTimeBetween(int managerId, LocalDateTime beginDate , LocalDateTime   endDate);
//    @Query("SELECT")
//    List<MenuDetails> getAll(int managerId)


}
