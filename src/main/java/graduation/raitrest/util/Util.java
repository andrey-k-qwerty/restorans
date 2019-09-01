package graduation.raitrest.util;

import graduation.raitrest.model.entities.MenuDetails;
import graduation.raitrest.model.to.MenuDetailTo;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    private Util() {
    }

    public static <T extends Comparable<? super T>> boolean isBetween(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) <= 0);
    }

    public static MenuDetailTo menuDetail_2_MenuDetailTo (MenuDetails menuDetail) {
        return new MenuDetailTo(menuDetail.getId(), menuDetail.getDescription(), menuDetail.getDateTime(),
                menuDetail.getTypeDish(), menuDetail.getQuantity(),menuDetail.getPrice(),menuDetail.getRestaurant().getId());
    }

    public static List<MenuDetailTo> menuDetail_2_MenuDetailTo(List<MenuDetails> menuDetails) {
        return menuDetails.stream().map(menuDetail -> menuDetail_2_MenuDetailTo(menuDetail)).collect(Collectors.toList());
    }

}
