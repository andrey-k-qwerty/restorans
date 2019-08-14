package graduation.raitrest.model.entities;

import graduation.raitrest.model.AbstractBaseEntity;
import graduation.raitrest.model.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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
    @JoinColumn(name = "RESTORAN_ID", nullable = false)
    @NotNull
    private Restoran restoran;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    @NotNull
    private User manager;

    public MenuDetails(Integer id, String description, Date dateTime, @NotNull Restoran restoran, @NotNull User manager) {
        super(id);
        this.description = description;
        this.dateTime = dateTime;
        this.restoran = restoran;
        this.manager = manager;
    }

    public MenuDetails() {
    }

    public MenuDetails(String description, Date dateTime, @NotNull Restoran restoran, @NotNull User manager) {
        this.description = description;
        this.dateTime = dateTime;
        this.restoran = restoran;
        this.manager = manager;
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

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public User getUser() {
        return manager;
    }

    public void setUser(User user) {
        this.manager = user;
    }


}
