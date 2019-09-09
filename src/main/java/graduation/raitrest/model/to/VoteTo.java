package graduation.raitrest.model.to;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class VoteTo extends BaseTo {

    private LocalDateTime dateTime = LocalDateTime.now();

    @NotNull
    private Integer RestaurantID;

    private Integer UserID;

    public VoteTo() {
    }

    public VoteTo(Integer id, Integer restaurantID) {
        super(id);
        RestaurantID = restaurantID;
    }
    public VoteTo( Integer restaurantID) {
       this(null,restaurantID);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getRestaurantID() {
        return RestaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        RestaurantID = restaurantID;
    }
}
