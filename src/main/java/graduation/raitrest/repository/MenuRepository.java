package graduation.raitrest.repository;


import graduation.raitrest.model.entities.Menu;

import java.util.List;

public interface MenuRepository {
    // null if not found, when updated

    Menu save(Menu menu, int userId);
    Menu save(Menu menu, int restoranID, int userId);
    // false if not found
    boolean delete(int id);

    boolean delete(int id, int userId);

    // null if not found
    Menu get(int id);

    Menu get(int id, int userId);


    List<Menu> getAll();

    List<Menu> getAll(int userId);
}