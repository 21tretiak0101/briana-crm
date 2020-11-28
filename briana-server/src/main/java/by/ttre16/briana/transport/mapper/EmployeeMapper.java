package by.ttre16.briana.transport.mapper;

import by.ttre16.briana.entity.Employee;
import by.ttre16.briana.transport.EmployeeTo;
import by.ttre16.briana.transport.mapper.post.AddressPostMapper;
import by.ttre16.briana.transport.mapper.post.OrganizationPostMapper;
import by.ttre16.briana.transport.mapper.post.PasswordPostMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        uses = {
                OrganizationPostMapper.class,
                AddressMapper.class,
                AddressPostMapper.class,
                PositionMapper.class,
                PasswordPostMapper.class
        }
)
public interface EmployeeMapper {
    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "password", ignore = true)
    EmployeeTo toDto(Employee created);

    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "registered", ignore = true)
    @Mapping(target = "photoPath", ignore = true)
    Employee toEntity(EmployeeTo employeeTo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registered", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "photoPath", ignore = true)
    @Mapping(target = "organization", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void copyProperties(@MappingTarget Employee target, EmployeeTo source);
}
