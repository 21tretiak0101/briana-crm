package by.ttre16.briana.repository;

import by.ttre16.briana.entity.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The repository class for {@link Address} entity.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see Repository
 * @see EntityManager
 */


@Repository
public class AddressRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Address save(Address address) {
        entityManager.persist(address);
        return address;
    }
}
