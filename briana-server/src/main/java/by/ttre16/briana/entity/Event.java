package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractRelatedToOrganizationEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries({
    @NamedQuery(
        name = Event.GET_BY_ID,
        query = "select e from Event e " +
                "where e.id =: id and e.organization.id =: organizationId"
    )
})
@Entity
@Getter
@Setter
@Table(name = "events")
@ToString
public class Event extends AbstractRelatedToOrganizationEntity {
    public static final String GET_BY_ID = "event:getById";

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
