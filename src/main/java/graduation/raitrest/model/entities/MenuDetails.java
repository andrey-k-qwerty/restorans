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

    @Basic
    @Column(name = "TYPE_DISH")
    private String typeDish;

    @Basic
    @Column(name = "QUANTITY")
    private Integer quantity;

    @Basic
    @Column(name = "TYPE_QUANTITY")
    private String typeQuantity;

    @Basic
    @Column(name = "PRICE",columnDefinition="Decimal(5,2) default '0.00'")
    private Float price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public MenuDetails(Integer id, @NotNull Restaurant restaurant, String typeDish, String description,
                       Integer quantity, String typeQuantity, Float price, Date dateTime) {
        super(id);
        this.restaurant = restaurant;
        this.typeDish = typeDish;
        this.description = description;
        this.quantity = quantity;
        this.typeQuantity = typeQuantity;
        this.price = price;
        this.dateTime = dateTime;
    }

    public MenuDetails(@NotNull Restaurant restaurant, String typeDish, String description,
                       Integer quantity, String typeQuantity, Float price, Date dateTime) {
       this(null,restaurant,typeDish,description,quantity,typeQuantity,price,dateTime);
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

    public String getTypeDish() {
        return typeDish;
    }

    public void setTypeDish(String typeDish) {
        this.typeDish = typeDish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTypeQuantity() {
        return typeQuantity;
    }

    public void setTypeQuantity(String typeQuantity) {
        this.typeQuantity = typeQuantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
