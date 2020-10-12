package by.ttre16.briana.data;

import by.ttre16.briana.entity.Category;

import java.util.HashMap;
import java.util.Map;

import static by.ttre16.briana.data.OrganizationTestData.*;

public class CategoryTestData {
    public static final Map<Integer, Category> CATEGORIES = new HashMap<>();
    public static final Integer CATEGORY18_ID = 18;
    public static final Integer CATEGORY19_ID = 19;
    public static final Integer CATEGORY20_ID = 20;

    static {
        Category category18 = new Category();
        category18.setId(CATEGORY18_ID);
        category18.setName("test-category-18");
        category18.setDescription("test-description:18");
        category18.setImagePath("path/to/image/18");
        category18.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));

        CATEGORIES.put(CATEGORY18_ID, category18);

        Category category19 = new Category();
        category19.setId(CATEGORY19_ID);
        category19.setName("test-category-19");
        category19.setDescription("test-description:19");
        category19.setImagePath("path/to/image/19");
        category19.setOrganization(ORGANIZATIONS.get(ORGANIZATION2_ID));

        CATEGORIES.put(CATEGORY19_ID, category19);

        Category category20 = new Category();
        category20.setId(CATEGORY20_ID);
        category20.setName("test-category-20");
        category20.setDescription("test-description:20");
        category20.setImagePath("path/to/image/20");
        category20.setOrganization(ORGANIZATIONS.get(ORGANIZATION2_ID));

        CATEGORIES.put(CATEGORY20_ID, category20);
    }
}
