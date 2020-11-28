package by.ttre16.briana.transport.mapper.post;

import by.ttre16.briana.entity.base.AbstractRelatedToOrganizationEntity;
import by.ttre16.briana.service.OrganizationService;
import by.ttre16.briana.transport.base.BaseTo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OrganizationPostMapper {
    @Autowired
    private OrganizationService organizationService;

    @AfterMapping
    protected void mapOrganization(
            BaseTo dto,
            @MappingTarget AbstractRelatedToOrganizationEntity target) {
        target.setOrganization(
                organizationService.get(
                        dto.getOrganizationId()
                )
        );
    }
}
