package by.ttre16.briana.data;

import by.ttre16.briana.entity.Address;

import java.util.HashMap;
import java.util.Map;

public class AddressTestData {
    public static final Map<Integer, Address> ADDRESSES = new HashMap<>();
    public static final Integer ADDRESS7_ID = 7;
    public static final Integer ADDRESS8_ID = 8;
    public static final Integer ADDRESS9_ID = 9;
    public static final Integer ADDRESS10_ID = 10;

    static {
        Address address7 = new Address();
        address7.setId(ADDRESS7_ID);
        address7.setCountry("France");
        address7.setCity("Paris");
        address7.setPostcode("324-122-7");

        ADDRESSES.put(ADDRESS7_ID, address7);

        Address address8 = new Address();
        address8.setId(ADDRESS8_ID);
        address8.setCountry("Germany");
        address8.setCity("Berlin");
        address8.setPostcode("324-122-8");

        ADDRESSES.put(ADDRESS8_ID, address8);

        Address address9 = new Address();
        address9.setId(ADDRESS9_ID);
        address9.setCountry("Russia");
        address9.setCity("Moscow");
        address9.setPostcode("324-122-9");

        ADDRESSES.put(ADDRESS9_ID, address9);

        Address address10 = new Address();
        address10.setId(ADDRESS10_ID);
        address10.setCountry("Poland");
        address10.setCity("Warsaw");
        address10.setPostcode("324-122-10");

        ADDRESSES.put(ADDRESS10_ID, address10);
    }
}
