package by.ttre16.briana.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class AbstractDescribedEntity extends AbstractNamedEntity {
    @Column(name = "description")
    private String description;
}
