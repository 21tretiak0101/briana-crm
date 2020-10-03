package by.ttre16.briana.entity.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class AbstractBaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"
    )
    @SequenceGenerator(
            name="sequence_generator",
            sequenceName = "briana_sequence",
            allocationSize = 1,
            initialValue = 1000
    )
    private Integer id;
}
