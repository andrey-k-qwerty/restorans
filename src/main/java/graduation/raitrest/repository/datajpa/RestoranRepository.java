package graduation.raitrest.repository.datajpa;


import graduation.raitrest.model.entities.Restoran;

import java.util.List;

public interface RestoranRepository {
    // null if not found, when updated
   Restoran save(Restoran restoran, int userId);

    // false if not found
    boolean delete(int id);

    boolean delete(int id, int userId);

    // null if not found
    Restoran get(int id);

    Restoran get(int id, int userId);

    Restoran getWithUser(int id);

    List<Restoran> getAll();

    List<Restoran> getAll(int userId);
}