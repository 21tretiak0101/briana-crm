package by.ttre16.briana.transport.mapper;

import by.ttre16.briana.entity.Client;
import by.ttre16.briana.transport.ClientTo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        uses = {
                OrganizationHelper.class,
                AddressHelper.class
        }
)
public interface ClientMapper {
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "organization", ignore = true)
    Client toEntity(ClientTo clientTo);

    @Mapping(target = "organizationId", source = "organization.id")
    ClientTo toDto(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "organization", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void copyProperties(@MappingTarget Client target, ClientTo source);
}
