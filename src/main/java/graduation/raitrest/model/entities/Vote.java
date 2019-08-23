package graduation.raitrest.model.entities;

import graduation.raitrest.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "VOTES")
public class Vote extends AbstractBaseEntity {

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Vote() {
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Menu getMenu() {
//        return menu;
//    }
//
//    public void setMenu(Menu menu) {
//        this.menu = menu;
//    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Vote(Integer id, LocalDateTime dateTime, @NotNull User user, @NotNull Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Vote(LocalDateTime dateTime, @NotNull User user, @NotNull Restaurant restaurant) {
        this(null,dateTime,user,restaurant);
    }

}
