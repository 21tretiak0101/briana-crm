package by.ttre16.briana.transport.mapper;

import by.ttre16.briana.entity.Order;
import by.ttre16.briana.transport.OrderTo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "created", ignore = true)
    Order toEntity(OrderTo orderTo);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "clientId", ignore = true)
    OrderTo toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void copyProperties(@MappingTarget Order target, Order source);
}
