package by.ttre16.briana.data;

import by.ttre16.briana.entity.Organization;

import java.util.HashMap;
import java.util.Map;

public class OrganizationTestData {
    public static final Map<Integer, Organization> ORGANIZATIONS =
            new HashMap<>();
    public static final Integer ORGANIZATION1_ID = 1;
    public static final Integer ORGANIZATION2_ID = 2;

    static {
        Organization organization1 = new Organization();
        organization1.setId(ORGANIZATION1_ID);
        organization1.setName("test-organization-1");
        organization1.setDescription("org.test.description-1");
        organization1.setCurrency("USD");

        ORGANIZATIONS.put(ORGANIZATION1_ID, organization1);

        Organization organization2 = new Organization();
        organization2.setId(ORGANIZATION2_ID);
        organization2.setName("test-organization-2");
        organization2.setDescription("org.test.description-2");
        organization2.setCurrency("EUR");

        ORGANIZATIONS.put(ORGANIZATION2_ID, organization2);
    }
}
