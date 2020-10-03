package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "clients")
public class Client extends AbstractUser {
    @Column(name = "created")
    private LocalDate created;

    @OneToMany
    @JoinColumn(name = "client_id")
    private List<Order> orders;
}
