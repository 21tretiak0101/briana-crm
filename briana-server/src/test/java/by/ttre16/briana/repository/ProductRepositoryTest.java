package by.ttre16.briana.repository;

import by.ttre16.briana.AbstractTest;
import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static by.ttre16.briana.data.CategoryTestData.*;
import static by.ttre16.briana.data.OrganizationTestData.*;
import static by.ttre16.briana.data.ProductTestData.*;

public class ProductRepositoryTest extends AbstractTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void save() {
        Product product = new Product();
        product.setName("test:name");
        product.setDescription("test:description");
        product.setImagePath("path/to/test");
        product.setPrice(1000.0);
        product.setCategory(CATEGORIES.get(CATEGORY18_ID));
        product.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));

        Product saved = productRepository.save(product);

        Assert.assertNotNull(saved);

        RecursiveAssert.assertMatch(
                saved,
                entityManager.find(Product.class, saved.getId())
        );
    }

    @Test
    @Transactional
    public void update() {
        Product product = new Product();
        BeanUtils.copyProperties(
                PRODUCTS.get(PRODUCT21_ID),
                product
        );
        product.setName("updated:name");
        Product updated = productRepository.update(product);

        Assert.assertNotNull(updated);
        RecursiveAssert.assertMatch(
                updated,
                entityManager.find(Product.class, updated.getId())
        );
    }

    @Test
    @Transactional(readOnly = true)
    public void get() {
        Product expected = PRODUCTS.get(PRODUCT22_ID);
        productRepository.get(
                expected.getId(),
                expected.getOrganization().getId()
        )
                .ifPresent(product -> RecursiveAssert.assertMatch(
                        expected,
                        product
                ));
    }

    @Test
    @Transactional(readOnly = true)
    public void getAll() {
        List<Product> products = PRODUCTS.values().stream()
                .filter(p ->
                        ORGANIZATION2_ID.equals(
                                p.getOrganization().getId()
                        )
                )
                .collect(Collectors.toList());

        RecursiveAssert.assertMatch(
                products,
                productRepository.getAll(ORGANIZATION2_ID)
        );
    }

    @Test
    @Transactional(readOnly = true)
    public void getByCategoryId() {
        List<Product> products = PRODUCTS.values().stream()
                .filter(p ->
                        ORGANIZATION2_ID.equals(
                                p.getOrganization().getId()
                        )
                )
                .filter(p ->
                        CATEGORY19_ID.equals(
                                p.getCategory().getId()
                        ))
                .collect(Collectors.toList());

        RecursiveAssert.assertMatch(
                products,
                productRepository.getByCategoryId(
                        CATEGORY19_ID,
                        ORGANIZATION2_ID
                )
        );
    }
}
