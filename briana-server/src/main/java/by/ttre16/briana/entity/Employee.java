package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employee extends AbstractUser {
    @Column(name = "registered")
    private LocalDate registered;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "password")
    private String password;
}
