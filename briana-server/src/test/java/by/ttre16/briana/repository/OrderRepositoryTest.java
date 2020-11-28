package by.ttre16.briana.repository;

import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.entity.Client;
import by.ttre16.briana.entity.Order;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static by.ttre16.briana.data.ClientTestData.CLIENT15_ID;
import static by.ttre16.briana.data.ClientTestData.CLIENTS;
import static by.ttre16.briana.data.OrderTestData.ORDER25_ID;
import static by.ttre16.briana.data.OrderTestData.ORDERS;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATION2_ID;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATIONS;
import static by.ttre16.briana.data.ProductTestData.*;

public class OrderRepositoryTest extends AbstractRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Transactional
    public void save() {
        Order order = new Order();
        order.setCreated(LocalDateTime.now());
        order.setOrganization(ORGANIZATIONS.get(ORGANIZATION2_ID));
        order.setProducts(
                List.of(
                        PRODUCTS.get(PRODUCT22_ID),
                        PRODUCTS.get(PRODUCT23_ID)
                )
        );

        Order saved = orderRepository.save(order);

        Assert.assertNotNull(saved.getId());
        RecursiveAssert.assertMatch(
                saved,
                entityManager.find(Order.class, saved.getId())
        );
    }

    @Test
    @Transactional
    public void update() {
        Order order = new Order();
        BeanUtils.copyProperties(
                ORDERS.get(ORDER25_ID),
                order
        );

        order.setCreated(LocalDateTime.of(2020, 10, 10, 10, 30));
        Order updated = orderRepository.update(order);

        RecursiveAssert.assertMatch(
                updated,
                entityManager.find(Order.class, updated.getId())
        );
    }

    @Test
    @Transactional(readOnly = true)
    public void getAllByClientId() {
        Client client = CLIENTS.get(CLIENT15_ID);

        RecursiveAssert.assertMatch(
                client.getOrders(),
                orderRepository.getAllByClientId(
                        client.getId(),
                        client.getOrganization().getId()
                ),
                "created"
        );
    }
}
