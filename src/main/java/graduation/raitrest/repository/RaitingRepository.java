package graduation.raitrest.repository;



import graduation.raitrest.model.entities.Raiting;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface RaitingRepository {
    // null if not found, when updated

    Raiting save(int menuId, int userId);
    Raiting save(int menu, Date dateTime, int userId);
    // false if not found
    boolean delete(int id);

    boolean delete(int id, int userId);

    // null if not found
    Raiting get(int id);

    Raiting get(int id, int userId);


    List<Raiting> getAll();

    List<Raiting> getAll(int userId);


    List<Raiting> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
    List<Raiting> getBetween(LocalDateTime startDate, LocalDateTime endDate);
// group by
// https://stackoverflow.com/questions/36328063/how-to-return-a-custom-object-from-a-spring-data-jpa-group-by-query
}
