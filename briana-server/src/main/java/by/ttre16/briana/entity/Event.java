package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractRelatedToOrganizationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "events")
public class Event extends AbstractRelatedToOrganizationEntity {
    @Column(name = "message")
    private String message;

    @Column(name = "published")
    private LocalDateTime published;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Employee publisher;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventType type;
}
