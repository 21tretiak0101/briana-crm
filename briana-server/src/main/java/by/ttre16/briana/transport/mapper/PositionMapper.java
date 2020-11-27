package by.ttre16.briana.transport.mapper;

import by.ttre16.briana.entity.Position;
import by.ttre16.briana.transport.PositionTo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    @Mapping(target = "organization", ignore = true)
    Position toEntity(PositionTo dto);

    @Mapping(target = "organizationId", source = "organization.id")
    PositionTo toDto(Position entity);

    @Mapping(target = "organization", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void copyProperties(@MappingTarget Position target, PositionTo source);
}
