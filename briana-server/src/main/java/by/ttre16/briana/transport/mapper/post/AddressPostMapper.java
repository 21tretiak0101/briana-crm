package by.ttre16.briana.transport.mapper.post;

import by.ttre16.briana.entity.base.AbstractUser;
import by.ttre16.briana.repository.AddressRepository;
import by.ttre16.briana.repository.EmployeeRepository;
import by.ttre16.briana.transport.base.UserTo;
import by.ttre16.briana.transport.mapper.AddressMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public abstract class AddressPostMapper {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private AddressMapper addressMapper;

    @AfterMapping
    public void mapAddress(
            UserTo source,
            @MappingTarget AbstractUser target) {
        if (isNull(target.getAddress().getId())) {
            target.setAddress(
                    addressRepository.save(
                            addressMapper.toEntity(source.getAddress())
                    )
            );
        }
    }
}
