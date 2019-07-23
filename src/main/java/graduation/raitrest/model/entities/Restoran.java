package graduation.raitrest.model.entities;

import graduation.raitrest.model.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RESTORANS")
public class Restoran extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Restoran(Integer id, String name, User user) {
        super(id, name);
        this.user = user;
    }

    public Restoran() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Restoran{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
