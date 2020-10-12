package by.ttre16.briana.data;

import by.ttre16.briana.entity.Client;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.ttre16.briana.data.AddressTestData.*;
import static by.ttre16.briana.data.OrderTestData.*;
import static by.ttre16.briana.data.OrganizationTestData.*;

public class ClientTestData {
    public static final Map<Integer, Client> CLIENTS = new HashMap<>();
    public static final Integer CLIENT15_ID = 15;
    public static final Integer CLIENT16_ID = 16;
    public static final Integer CLIENT17_ID = 17;

    static {
        Client client15 = new Client();
        client15.setId(CLIENT15_ID);
        client15.setName("test-client-15");
        client15.setPhone("566-77-15");
        client15.setEmail("test-15@gmail.com");
        client15.setCreated(LocalDate.now());
        client15.setDescription("desc:org1");
        client15.setAddress(ADDRESSES.get(ADDRESS9_ID));
        client15.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));
        client15.setOrders(
                List.of(
                        ORDERS.get(ORDER24_ID)
                )
        );

        CLIENTS.put(CLIENT15_ID, client15);

        Client client16 = new Client();
        client16.setId(CLIENT16_ID);
        client16.setName("test-client-16");
        client16.setPhone("566-77-16");
        client16.setEmail("test-16@gmail.com");
        client16.setCreated(LocalDate.now());
        client16.setDescription("desc:org1");
        client16.setAddress(ADDRESSES.get(ADDRESS8_ID));
        client16.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));
        client16.setOrders(
                List.of(
                        ORDERS.get(ORDER25_ID)
                )
        );

        CLIENTS.put(CLIENT16_ID, client16);

        Client client17 = new Client();
        client17.setId(CLIENT17_ID);
        client17.setName("test-client-17");
        client17.setPhone("566-77-17");
        client17.setEmail("test-17@gmail.com");
        client17.setCreated(LocalDate.now());
        client17.setDescription("desc:org2");
        client17.setAddress(ADDRESSES.get(ADDRESS10_ID));
        client17.setOrganization(ORGANIZATIONS.get(ORGANIZATION2_ID));

        CLIENTS.put(CLIENT17_ID, client17);
    }
}
