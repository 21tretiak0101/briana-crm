package by.ttre16.briana.transport.mapper;

import by.ttre16.briana.entity.Category;
import by.ttre16.briana.transport.CategoryTo;
import by.ttre16.briana.transport.mapper.post.OrganizationPostMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring", uses = {OrganizationPostMapper.class})
public interface CategoryMapper {
    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "imagePath", ignore = true)
    Category toEntity(CategoryTo dto);

    @Mapping(target = "organizationId", source = "organization.id")
    CategoryTo toDto(Category entity);

    @Mapping(target = "imagePath", ignore = true)
    @Mapping(target = "organization", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void copyProperties(@MappingTarget Category target, CategoryTo source);
}
