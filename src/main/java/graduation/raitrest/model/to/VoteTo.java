package graduation.raitrest.model.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class VoteTo extends BaseTo {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteTo)) return false;
        VoteTo voteTo = (VoteTo) o;
        return Objects.equals(dateTime, voteTo.dateTime) &&
                Objects.equals(RestaurantID, voteTo.RestaurantID) &&
                Objects.equals(UserID, voteTo.UserID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, RestaurantID, UserID);
    }
}
