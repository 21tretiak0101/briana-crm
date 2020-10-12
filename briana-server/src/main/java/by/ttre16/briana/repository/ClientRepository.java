package by.ttre16.briana.repository;

import by.ttre16.briana.entity.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * The repository class for {@link Client} entity.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see Repository
 * @see EntityManager
 */

@Repository
public class ClientRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Client save(Client client) {
        entityManager.persist(client);
        return client;
    }

    public Client update(Client client) {
        return entityManager.merge(client);
    }

    public Optional<Client> get(Integer id, Integer organizationId) {
        return entityManager.createNamedQuery(
                Client.GET_BY_ID,
                Client.class
        )
                .setParameter("id", id)
                .setParameter("organizationId", organizationId)
                .getResultList().stream()
                .findFirst();
    }

    public List<Client> getAll(Integer organizationId) {
        return entityManager.createNamedQuery(
                Client.GET_ALL,
                Client.class
        )
                .setParameter("organizationId", organizationId)
                .getResultList();
    }
}
