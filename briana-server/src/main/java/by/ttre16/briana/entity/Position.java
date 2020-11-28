package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractDescribedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
    @NamedQuery(
        name = Position.GET_BY_ID,
        query = "select p from Position p " +
                "where p.id =: id and p.organization.id =: organizationId"
    )
})
@Entity
@Table(name = "positions")
@Getter
@Setter
public class Position extends AbstractDescribedEntity {
    public static final String GET_BY_ID = "position:getById";

    @ElementCollection
    @CollectionTable(
            name = "positions_permissions",
            joinColumns = @JoinColumn(name = "position_id")
    )
    @Column(name = "permission")
    private List<String> permissions;
}
