package by.ttre16.briana.repository;

import by.ttre16.briana.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * The repository class for {@link Product} entity.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see Repository
 * @see EntityManager
 */

@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Product save(Product product) {
        entityManager.persist(product);
        return product;
    }

    public Product update(Product product) {
        return entityManager.merge(product);
    }

    public Optional<Product> get(Integer id, Integer organizationId) {
        return entityManager.createNamedQuery(
                Product.GET_BY_ID,
                Product.class
        )
                .setParameter("id", id)
                .setParameter("organizationId", organizationId)
                .getResultList().stream()
                .findFirst();
    }

    public List<Product> getAll(Integer organizationId) {
        return entityManager.createNamedQuery(
                Product.GET_ALL,
                Product.class
        )
                .setParameter("organizationId", organizationId)
                .getResultList();
    }

    public List<Product> getByCategoryId(
            Integer categoryId,
            Integer organizationId) {
        return entityManager.createNamedQuery(
                Product.GET_BY_CATEGORY_ID,
                Product.class
        )
                .setParameter("categoryId", categoryId)
                .setParameter("organizationId", organizationId)
                .getResultList();
    }
}
