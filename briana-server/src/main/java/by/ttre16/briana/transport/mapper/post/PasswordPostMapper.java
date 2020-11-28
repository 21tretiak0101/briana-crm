package by.ttre16.briana.transport.mapper.post;

import by.ttre16.briana.entity.Employee;
import by.ttre16.briana.transport.base.HasPassword;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
public abstract class PasswordPostMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @AfterMapping
    protected void encodePassword(
            HasPassword dto,
            @MappingTarget Employee target) {
        if (isNull(target.getId())) {
            target.setPassword(passwordEncoder.encode(target.getPassword()));
        }
    }
}
