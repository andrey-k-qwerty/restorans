package graduation.raitrest.repository;



import graduation.raitrest.model.entities.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    // null if not found, when updated

    Vote save(int menuId, int userId);

    // false if not found
    boolean delete(int id);

    boolean delete(int id, int userId);

    // null if not found
    Vote get(int id);

    Vote get(int id, int userId);


    List<Vote> getAll();

    List<Vote> getAll(int userId);


    List<Vote> filterByDateTime(LocalDateTime startDate, LocalDateTime endDate, int userId);
    List<Vote> filterByDateTime(LocalDateTime startDate, LocalDateTime endDate);
// group by
// https://stackoverflow.com/questions/36328063/how-to-return-a-custom-object-from-a-spring-data-jpa-group-by-query
}
