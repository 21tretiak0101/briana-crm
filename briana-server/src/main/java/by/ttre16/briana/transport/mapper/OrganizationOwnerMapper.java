package by.ttre16.briana.transport.mapper;

import by.ttre16.briana.entity.Employee;
import by.ttre16.briana.entity.Organization;
import by.ttre16.briana.transport.OrganizationOwner;
import by.ttre16.briana.transport.mapper.post.PasswordPostMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PasswordPostMapper.class)
public interface OrganizationOwnerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", source = "organizationDescription")
    @Mapping(target = "currency", source = "organizationCurrency")
    @Mapping(target = "name", source = "organizationName")
    Organization getOrganization(OrganizationOwner dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "registered", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "description", ignore = true)
    Employee getOwner(OrganizationOwner entity);
}
