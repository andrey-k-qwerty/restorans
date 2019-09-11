package graduation.raitrest.model.to;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class RestaurantTo extends BaseTo{

    @NotBlank
    private String address;

    private String owner;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered = new Date();

    private String description;

    @NotNull
    private Integer managerID;

    public RestaurantTo(Integer id,  String address, String owner,  Date registered, String description, Integer managerID) {
        super(id);
        this.address = address;
        this.owner = owner;
        this.registered = registered;
        this.description = description;
        this.managerID = managerID;
    }
    public RestaurantTo() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getManagerID() {
        return managerID;
    }

    public void setManagerID(Integer managerID) {
        this.managerID = managerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantTo)) return false;
        RestaurantTo that = (RestaurantTo) o;
        return  Objects.equals(id, that.id) &&
                Objects.equals(address, that.address) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(registered, that.registered) &&
                Objects.equals(description, that.description) &&
                Objects.equals(managerID, that.managerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,address, owner, registered, description, managerID);
    }
}
