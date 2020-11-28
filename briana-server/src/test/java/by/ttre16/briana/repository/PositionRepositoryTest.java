package by.ttre16.briana.repository;

import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.entity.EmployeePermission;
import by.ttre16.briana.entity.Position;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATION2_ID;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATIONS;
import static by.ttre16.briana.data.PositionTestData.POSITION3_ID;
import static by.ttre16.briana.data.PositionTestData.POSITIONS;

public class PositionRepositoryTest extends AbstractRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PositionRepository positionRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void save() {
        Position position = new Position();
        position.setName("test:position");
        position.setDescription("test:description");
        position.setPermissions(
                List.of(
                        EmployeePermission.PRODUCT_READ.getName(),
                        EmployeePermission.CATEGORY_READ.getName()
                )
        );
        position.setOrganization(ORGANIZATIONS.get(ORGANIZATION2_ID));

        Position saved = positionRepository.save(position);

        Assert.assertNotNull(saved);
        RecursiveAssert.assertMatch(
                saved,
                entityManager.find(Position.class, saved.getId())
        );
    }

    @Test
    @Transactional
    @DirtiesContext
    public void update() {
        Position position = new Position();
        BeanUtils.copyProperties(
                POSITIONS.get(POSITION3_ID),
                position
        );
        position.setName("updated:name");

        Position updated = positionRepository.update(position);

        Assert.assertNotNull(updated);
        RecursiveAssert.assertMatch(
                updated,
                entityManager.find(Position.class, updated.getId())
        );
    }
}
