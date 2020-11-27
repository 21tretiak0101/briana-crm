package by.ttre16.briana.transport.mapper;

import by.ttre16.briana.entity.Product;
import by.ttre16.briana.transport.ProductTo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductTo dto);

    @Mapping(target = "organizationId", source = "organization.id")
    ProductTo toDto(Product entity);

    @Mapping(target = "imagePath", ignore = true)
    @Mapping(target = "organization", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void copyProperties(@MappingTarget Product entity, ProductTo dto);
}
