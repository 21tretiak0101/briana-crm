package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NamedQueries({
    @NamedQuery(
        name = Client.GET_BY_ID,
        query = "select c from Client c " +
                "where c.id =: id and c.organization.id =: organizationId"
    ),
    @NamedQuery(
        name = Client.GET_ALL,
        query = "select c from Client c " +
                "where c.organization.id =: organizationId"
    )
})
@Entity
@Getter
@Setter
@Table(name = "clients")
@ToString
public class Client extends AbstractUser {
    public final static String GET_ALL = "client:getAll";

    public final static String GET_BY_ID = "client:getById";

    @Column(name = "created")
    private LocalDate created;

    @OneToMany
    @JoinTable(
            name = "clients_orders",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders;
}
