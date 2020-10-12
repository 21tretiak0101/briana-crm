package by.ttre16.briana.data;

import by.ttre16.briana.entity.Product;

import java.util.HashMap;
import java.util.Map;

import static by.ttre16.briana.data.CategoryTestData.*;
import static by.ttre16.briana.data.OrganizationTestData.*;

public class ProductTestData {
    public static final Map<Integer, Product> PRODUCTS = new HashMap<>();
    public static final Integer PRODUCT21_ID = 21;
    public static final Integer PRODUCT22_ID = 22;
    public static final Integer PRODUCT23_ID = 23;

    static {
        Product product21 = new Product();
        product21.setId(PRODUCT21_ID);
        product21.setName("laptop");
        product21.setDescription("description:21");
        product21.setPrice(210.0);
        product21.setImagePath("path/21");
        product21.setCategory(CATEGORIES.get(CATEGORY18_ID));
        product21.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));

        PRODUCTS.put(PRODUCT21_ID, product21);

        Product product22 = new Product();
        product22.setId(PRODUCT22_ID);
        product22.setName("laptop");
        product22.setDescription("description:22");
        product22.setPrice(220.0);
        product22.setImagePath("path/22");
        product22.setCategory(CATEGORIES.get(CATEGORY19_ID));
        product22.setOrganization(ORGANIZATIONS.get(ORGANIZATION2_ID));

        PRODUCTS.put(PRODUCT22_ID, product22);

        Product product23 = new Product();
        product23.setId(PRODUCT23_ID);
        product23.setName("pencil");
        product23.setDescription("description:23");
        product23.setPrice(230.0);
        product23.setImagePath("path/23");
        product23.setCategory(CATEGORIES.get(CATEGORY20_ID));
        product23.setOrganization(ORGANIZATIONS.get(ORGANIZATION2_ID));

        PRODUCTS.put(PRODUCT23_ID, product23);
    }
}
