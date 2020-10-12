package by.ttre16.briana.data;

import by.ttre16.briana.entity.Order;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATION1_ID;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATIONS;
import static by.ttre16.briana.data.ProductTestData.*;

public class OrderTestData {
    public static final Map<Integer, Order> ORDERS = new HashMap<>();
    public static final Integer ORDER24_ID = 24;
    public static final Integer ORDER25_ID = 25;

    static {
        Order order24 = new Order();
        order24.setId(ORDER24_ID);
        order24.setCreated(LocalDateTime.now());
        order24.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));
        order24.setProducts(
                List.of(
                        PRODUCTS.get(PRODUCT21_ID)
                )
        );

        ORDERS.put(ORDER24_ID, order24);

        Order order25 = new Order();
        order25.setId(ORDER25_ID);
        order25.setCreated(LocalDateTime.now());
        order25.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));
        order25.setProducts(
                List.of(
                        PRODUCTS.get(PRODUCT22_ID),
                        PRODUCTS.get(PRODUCT23_ID)
                )
        );

        ORDERS.put(ORDER25_ID, order25);
    }
}
