package by.ttre16.briana.repository;

import by.ttre16.briana.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * The repository class for {@link Category} entity.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see Repository
 * @see EntityManager
 */

@Repository
public class CategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Category save(Category category) {
        entityManager.persist(category);
        return category;
    }

    public Category update(Category category) {
        return entityManager.merge(category);
    }

    public Optional<Category> get(Integer id, Integer organizationId) {
        return entityManager.createNamedQuery(
                Category.GET_BY_ID,
                Category.class
        )
                .setParameter("id", id)
                .setParameter("organizationId", organizationId)
                .getResultList().stream()
                .findFirst();
    }
}
