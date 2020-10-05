package by.ttre16.briana.repository;

import by.ttre16.briana.AbstractTest;
import by.ttre16.briana.entity.Category;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class CategoryRepositoryTest extends AbstractTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void save() {
        Category category = new Category();
        category.setName("test category");
        Category savedCategory = categoryRepository.save(category);
        Assert.assertNotNull(savedCategory.getId());
        Assert.assertEquals(
            savedCategory.getId(),
            entityManager.find(Category.class, savedCategory.getId()).getId()
        );
    }

    @Test
    public void update() {
        Category category = entityManager.find(Category.class, 1);
        String updatedName = "updated_category_name";
        category.setName(updatedName);
        Category updated = categoryRepository.update(category);
        Assert.assertEquals(
                updated.getName(),
                updatedName
        );
    }

    @Test
    public void delete() {
        Category category = entityManager.find(Category.class, 1);
        Assert.assertTrue(
                categoryRepository.delete(
                        category.getId(),
                        category.getOrganization().getId()
                )
        );
        entityManager.clear();
        Assert.assertNull(entityManager.find(Category.class, 1));
    }
}
