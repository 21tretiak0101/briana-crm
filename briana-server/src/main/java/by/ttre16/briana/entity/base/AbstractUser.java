package by.ttre16.briana.entity.base;

import by.ttre16.briana.entity.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Setter
@Getter
@MappedSuperclass
public class AbstractUser extends AbstractDescribedEntity {
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
