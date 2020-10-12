package by.ttre16.briana.repository;

import by.ttre16.briana.entity.Organization;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * The repository class for {@link Organization} entity.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see Repository
 * @see EntityManager
 */

@Repository
public class OrganizationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Organization save(Organization organization) {
        entityManager.persist(organization);
        return organization;
    }

    public Organization update(Organization organization) {
        return entityManager.merge(organization);
    }

    public Optional<Organization> get(Integer id) {
        return ofNullable(
                entityManager.find(Organization.class, id)
        );
    }
}
