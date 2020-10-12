package by.ttre16.briana.repository;

import by.ttre16.briana.entity.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * The repository class for {@link Event} entity.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see Repository
 * @see EntityManager
 */

@Repository
public class EventRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Event save(Event event) {
        entityManager.persist(event);
        return event;
    }

    public Optional<Event> get(Integer id, Integer organizationId) {
        return entityManager.createNamedQuery(
                Event.GET_BY_ID,
                Event.class
        )
                .setParameter("id", id)
                .setParameter("organizationId", organizationId)
                .getResultList().stream()
                .findFirst();
    }
}
