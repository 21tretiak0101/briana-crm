package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractDescribedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "positions")
@Getter
@Setter
public class Position extends AbstractDescribedEntity {
    @OneToMany
    @JoinTable(
            name = "positions_permissions",
            joinColumns = @JoinColumn(name = "position_id")
    )
    private List<Permission> permissions;
}