package by.ttre16.briana.entity.base;

import by.ttre16.briana.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class AbstractRelatedToOrganizationEntity extends AbstractBaseEntity {
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
