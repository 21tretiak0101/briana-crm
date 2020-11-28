package by.ttre16.briana.transport.mapper;

import by.ttre16.briana.entity.Organization;
import by.ttre16.briana.transport.OrganizationTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    OrganizationTo toDto(Organization organization);
}
