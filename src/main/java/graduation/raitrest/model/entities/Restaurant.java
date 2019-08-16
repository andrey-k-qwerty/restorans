package graduation.raitrest.model.entities;

import graduation.raitrest.model.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RESTAURANTS")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "ADDRESS", nullable = false)
    @NotBlank
    String address;

    @Column(name = "OWNER" )
    String owner;

    @Column(name = "REGISTERED", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered = new Date();

    @Column(name = "DESCRIPTION" )
    String description;

    @ManyToMany ( mappedBy = "restaurants" )
    private List<User> users ;

}
