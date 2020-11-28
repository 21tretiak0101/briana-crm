package by.ttre16.briana.repository;

import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.entity.Category;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static by.ttre16.briana.data.CategoryTestData.CATEGORIES;
import static by.ttre16.briana.data.CategoryTestData.CATEGORY18_ID;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATION1_ID;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATIONS;

public class CategoryRepositoryTest extends AbstractRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void save() {
        Category category = new Category();
        category.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));
        category.setDescription("test:description");
        category.setImagePath("path/to/image/100");

        Category saved = categoryRepository.save(category);

        Assert.assertNotNull(saved);
        RecursiveAssert.assertMatch(
                saved,
                entityManager.find(Category.class, saved.getId())
        );
    }

    @Test
    @Transactional
    @DirtiesContext
    public void update() {
        Category category = new Category();
        BeanUtils.copyProperties(
                CATEGORIES.get(CATEGORY18_ID),
                category
        );

        Category updated = categoryRepository.update(category);
        RecursiveAssert.assertMatch(
                updated,
                entityManager.find(Category.class, updated.getId())
        );
    }
}
