package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractRelatedToOrganizationEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@ToString
public class Order extends AbstractRelatedToOrganizationEntity {
    @Column(name = "created")
    private LocalDateTime created;

    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @ManyToMany
    private List<Product> products;
}
