package by.ttre16.briana.repository;

import by.ttre16.briana.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The repository class for {@link Order} entity.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see Repository
 * @see EntityManager
 */

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final ClientRepository clientRepository;

    public Order save(Order order) {
        entityManager.persist(order);
        return order;
    }

    public Order update(Order order) {
        return entityManager.merge(order);
    }

    public List<Order> getAllByClientId(
            Integer clientId,
            Integer organizationId) {
        return clientRepository.get(clientId, organizationId)
                .flatMap(client -> Optional.of(client.getOrders()))
                .orElse(Collections.emptyList());
    }
}
