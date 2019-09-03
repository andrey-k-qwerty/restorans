package graduation.raitrest.model.to;

import java.time.LocalDateTime;

public class VoteTo extends BaseTo {
    private LocalDateTime dateTime;
    private Integer RestaurantID;


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
