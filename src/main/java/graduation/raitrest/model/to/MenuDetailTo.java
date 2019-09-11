package graduation.raitrest.model.to;

import graduation.raitrest.model.entities.Restaurant;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class MenuDetailTo extends BaseTo{

    @NotBlank
    private String description;

    private LocalDateTime dateTime;

    private String typeDish;

    private String quantity;

    private BigDecimal price;

    private Integer restaurantID;

    public MenuDetailTo(Integer id, String description, LocalDateTime dateTime, String typeDish, String quantity, BigDecimal price, Integer restaurantID) {
        super(id);
        this.description = description;
        this.dateTime = dateTime;
        this.typeDish = typeDish;
        this.quantity = quantity;
        this.price = price;
        this.restaurantID = restaurantID;
    }

    public MenuDetailTo() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
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

    public Integer getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }

    @Override
    public String toString() {
        return "MenuDetailTo{" +
                "id=" + id +
                ", restaurantID=" + restaurantID +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", typeDish='" + typeDish + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuDetailTo)) return false;
        MenuDetailTo that = (MenuDetailTo) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(id, that.id) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(typeDish, that.typeDish) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(price, that.price) &&
                Objects.equals(restaurantID, that.restaurantID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, dateTime, typeDish, quantity, price, restaurantID);
    }
}
