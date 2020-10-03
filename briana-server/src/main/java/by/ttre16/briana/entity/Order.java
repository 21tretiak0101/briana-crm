package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractRelatedToOrganizationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends AbstractRelatedToOrganizationEntity {
    @Column(name = "created")
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
