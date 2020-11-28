package by.ttre16.briana.repository;

import by.ttre16.briana.entity.Position;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * The repository class for {@link Position} entity.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see Repository
 * @see EntityManager
 */

@Repository
public class PositionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Position save(Position position) {
        entityManager.persist(position);
        return position;
    }

    public Position update(Position position) {
        return entityManager.merge(position);
    }

    public Optional<Position> get(Integer id, Integer organizationId) {
        return entityManager.createNamedQuery(
                Position.GET_BY_ID,
                Position.class
        )
                .setParameter("id", id)
                .setParameter("organizationId", organizationId)
                .getResultList().stream()
                .findFirst();
    }
}
