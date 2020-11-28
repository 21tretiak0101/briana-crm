package by.ttre16.briana.repository;

import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.entity.Client;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static by.ttre16.briana.data.AddressTestData.ADDRESS9_ID;
import static by.ttre16.briana.data.AddressTestData.ADDRESSES;
import static by.ttre16.briana.data.ClientTestData.*;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATION1_ID;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATIONS;

public class ClientRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    @DirtiesContext
    public void save() {
        Client client = new Client();
        client.setName("test:name");
        client.setPhone("123-00-00");
        client.setEmail("test@mail.com");
        client.setCreated(LocalDate.now());
        client.setDescription("test:description");
        client.setAddress(ADDRESSES.get(ADDRESS9_ID));
        client.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));

        Client saved = clientRepository.save(client);

        Assert.assertNotNull(saved);
        RecursiveAssert.assertMatch(
                entityManager.find(Client.class, saved.getId()),
                saved
        );
    }

    @Test
    @Transactional
    @DirtiesContext
    public void update() {
        Client client = new Client();
        BeanUtils.copyProperties(
                CLIENTS.get(CLIENT15_ID),
                client
        );

        client.setEmail("updated@gmail.com");
        Client updated = clientRepository.update(client);

        System.out.println(updated.getId());

        RecursiveAssert.assertMatch(
                updated,
                entityManager.find(Client.class, updated.getId())
        );
    }

    @Test
    @Transactional(readOnly = true)
    public void get() {
        Client expected = CLIENTS.get(CLIENT16_ID);
        clientRepository.get(
                expected.getId(),
                expected.getOrganization().getId()
        )
                .ifPresent(client -> RecursiveAssert.assertMatch(
                        expected,
                        client,
                        "orders.created"
                ));
    }

    @Test
    @Transactional(readOnly = true)
    public void getAll() {
        List<Client> expected = CLIENTS.values().stream()
                .filter(client ->
                        ORGANIZATION1_ID.equals(
                                client.getOrganization().getId()
                        )
                )
                .collect(Collectors.toList());

        RecursiveAssert.assertMatch(
                expected,
                clientRepository.getAll(ORGANIZATION1_ID),
                "orders.created"
        );
    }

    @Test
    public void testProperties() {
        Client client = CLIENTS.get(CLIENT16_ID);
        Client test = new Client();
        BeanUtils.copyProperties(
                client,
                test
        );

        Assert.assertNotEquals(
                client,
                test
        );
    }
}
