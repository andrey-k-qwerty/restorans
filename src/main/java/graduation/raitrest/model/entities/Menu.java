package graduation.raitrest.model.entities;

import graduation.raitrest.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MENUS")
public class Menu extends AbstractBaseEntity {

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "DATE_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menus_details_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Menu(String description, Date dateTime, @NotNull Restaurant restaurant) {
        this.description = description;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public Menu(String description, Date dateTime) {
        this.description = description;
        this.dateTime = dateTime;

    }

    public Menu(Integer id, String description, Date dateTime, @NotNull Restaurant restaurant) {
        super(id);
        this.description = description;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
    }

    public Menu(Integer id, String description, Date dateTime) {
        super(id);
        this.description = description;
        this.dateTime = dateTime;
    }

    public Menu() {

    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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


}
