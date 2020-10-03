package by.ttre16.briana.entity.base;

import by.ttre16.briana.entity.Organization;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class AbstractRelatedToOrganizationEntity extends AbstractBaseEntity {
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
