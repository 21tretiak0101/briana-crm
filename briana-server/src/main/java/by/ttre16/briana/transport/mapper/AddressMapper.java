package by.ttre16.briana.transport.mapper;

import by.ttre16.briana.entity.Address;
import by.ttre16.briana.transport.AddressTo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressTo toDto(Address address);

    @Mapping(target = "id", ignore = true)
    Address toEntity(AddressTo addressTo);
}
