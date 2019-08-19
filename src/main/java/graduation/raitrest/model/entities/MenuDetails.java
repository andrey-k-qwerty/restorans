package graduation.raitrest.model.entities;

import graduation.raitrest.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MENU_DETAILS")
public class MenuDetails extends AbstractBaseEntity {

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    @NotNull
    private Restaurant restaurant;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "manager_id", nullable = false)
//    @NotNull
//    private User manager;

    public MenuDetails(Integer id, String description, Date dateTime, @NotNull Restaurant restaurant, @NotNull User manager) {
        super(id);
        this.description = description;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
  //      this.manager = manager;
    }

    public MenuDetails() {
    }

    public MenuDetails(String description, Date dateTime, @NotNull Restaurant restaurant, @NotNull User manager) {
        this.description = description;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
//        this.manager = manager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Date getDateTime() {
        return dateTime;
    }


    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

//    public User getUser() {
//        return manager;
//    }
//
//    public void setUser(User user) {
//        this.manager = user;
//    }


}
