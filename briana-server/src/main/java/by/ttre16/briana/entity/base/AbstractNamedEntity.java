package by.ttre16.briana.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class AbstractNamedEntity extends AbstractRelatedToOrganizationEntity {
    @Column(name = "name")
    private String name;
}
