package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The JavaBean domain object that represents
 * an {@link by.ttre16.briana.entity.base.AbstractUser} address.
 *
 * @author Ilia Tretiak
 * @version 1.0
 */

@Entity
@Getter
@Setter
@Table(name = "addresses")
public class Address extends AbstractBaseEntity {
    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postcode;
}
