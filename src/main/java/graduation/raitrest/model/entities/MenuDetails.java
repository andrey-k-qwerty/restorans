package graduation.raitrest.model.entities;

import graduation.raitrest.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    private String quantity;



    @Basic
    @Column(name = "PRICE", precision=10, scale=2,columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal  price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public MenuDetails(Integer id, @NotNull Restaurant restaurant, String typeDish, String description,
                       String quantity, BigDecimal price, Date dateTime) {
        super(id);
        this.restaurant = restaurant;
        this.typeDish = typeDish;
        this.description = description;
        this.quantity = quantity;

        this.price = price;
        this.dateTime = dateTime;
    }

    public MenuDetails(@NotNull Restaurant restaurant, String typeDish, String description,
                       String quantity,  BigDecimal price, Date dateTime) {
       this(null,restaurant,typeDish,description,quantity,price,dateTime);
    }

    public MenuDetails() {
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }



    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
