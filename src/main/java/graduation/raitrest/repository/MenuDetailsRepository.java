package graduation.raitrest.repository;

import graduation.raitrest.model.entities.MenuDetails;

import java.util.List;

public interface MenuDetailsRepository {

   MenuDetails save(MenuDetails menu, int restaurantID, int managerId);
   MenuDetails save(MenuDetails menu, int managerId);
    // false if not found
    boolean delete(int id);

    boolean delete(int id, int managerId);

    // null if not found
    MenuDetails get(int id);

    MenuDetails get(int id, int managerId);


    List<MenuDetails> getAll();
    List<MenuDetails> getAllToday();

    List<MenuDetails> getAll(int managerId);
    List<MenuDetails> getAll(int restaurantID, int managerId);

}
