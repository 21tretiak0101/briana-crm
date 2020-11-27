package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQueries({
    @NamedQuery(
        name = Employee.GET_BY_EMAIL,
        query = "select e from Employee e where e.email =: email"
    ),
    @NamedQuery(
        name = Employee.GET_ALL,
        query = "select e from Employee e " +
                "where e.organization.id =: organizationId"
    ),
    @NamedQuery(
        name = Employee.GET_BY_ID,
        query = "select e from Employee e " +
                "where e.id =: id and e.organization.id =: organizationId"
    )
})
@Getter
@Setter
@Entity
@Table(name = "employees")
@ToString
public class Employee extends AbstractUser {
    public final static String GET_BY_ID = "employee:getById";

    public final static String GET_ALL = "employee:getAll";

    public final static String GET_BY_EMAIL = "employee:getByEmail";

    @Column(name = "registered")
    private LocalDate registered = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "enabled")
    private Boolean enabled = true;

    @Column(name = "password")
    private String password;
}
