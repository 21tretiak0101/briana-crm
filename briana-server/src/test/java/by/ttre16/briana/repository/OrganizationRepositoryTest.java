package by.ttre16.briana.repository;

import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.entity.Organization;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static by.ttre16.briana.data.OrganizationTestData.*;

public class OrganizationRepositoryTest extends AbstractRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void save() {
        Organization organization = new Organization();
        organization.setCurrency("USD");
        organization.setName("test:organization");
        organization.setDescription("test:description");

        Organization saved = organizationRepository.save(organization);

        Assert.assertNotNull(saved);

        RecursiveAssert.assertMatch(
                saved,
                entityManager.find(Organization.class, saved.getId())
        );

    }

    @Test
    @Transactional
    @DirtiesContext
    public void update() {
        Organization organization = new Organization();
        BeanUtils.copyProperties(
            ORGANIZATIONS.get(ORGANIZATION1_ID),
                organization
        );

        organization.setDescription("updated:description");
        organization.setCurrency("EUR");

        Organization updated = organizationRepository.update(organization);

        RecursiveAssert.assertMatch(
                updated,
                entityManager.find(Organization.class, updated.getId())
        );
    }

    @Test
    @Transactional(readOnly = true)
    public void get() {
        Organization expected = ORGANIZATIONS.get(ORGANIZATION2_ID);

        organizationRepository.get(expected.getId())
                .ifPresent(organization -> RecursiveAssert.assertMatch(
                        expected,
                        organization
                ));
    }
}
