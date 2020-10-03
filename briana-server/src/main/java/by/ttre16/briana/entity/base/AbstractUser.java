package by.ttre16.briana.entity.base;

import by.ttre16.briana.entity.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@MappedSuperclass
public class AbstractUser extends AbstractDescribedEntity {
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @Column(name = "phone")
    private Address address;
}
