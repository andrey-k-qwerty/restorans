package graduation.raitrest.model.entities;

import graduation.raitrest.model.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MENUS")
public class Menu extends AbstractBaseEntity {

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTORAN_ID", nullable = false)
    @NotNull
    private Restoran restoran;



    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
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
