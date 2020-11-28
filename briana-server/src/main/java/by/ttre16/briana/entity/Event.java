package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractRelatedToOrganizationEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class Event extends AbstractRelatedToOrganizationEntity {
    public static final String GET_BY_ID = "event:getById";

    public Event(
            Employee publisher,
            EventType type,
            MessageTopic topic,
            Integer processedEntityId) {
        super(publisher.getOrganization());
        this.publisher = publisher;
        this.type = type;
        this.message = topic.getName() + ":" + processedEntityId.toString();
    }

    @Column(name = "message")
    private String message;

    @Column(name = "published")
    private LocalDateTime published = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Employee publisher;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventType type;
}
