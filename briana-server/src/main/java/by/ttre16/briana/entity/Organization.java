package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "organizations")
public class Organization extends AbstractBaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "currency")
    private String currency;
}
