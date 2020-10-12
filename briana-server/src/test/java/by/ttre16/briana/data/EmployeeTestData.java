package by.ttre16.briana.data;

import by.ttre16.briana.entity.Employee;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static by.ttre16.briana.data.AddressTestData.*;
import static by.ttre16.briana.data.OrganizationTestData.*;
import static by.ttre16.briana.data.PositionTestData.*;

public class EmployeeTestData {
    public static final Map<Integer, Employee> EMPLOYEES = new HashMap<>();
    public static final Integer EMPLOYEE11_ID = 11;
    public static final Integer EMPLOYEE12_ID = 12;
    public static final Integer EMPLOYEE13_ID = 13;
    public static final Integer EMPLOYEE14_ID = 14;

    static {
        Employee employee11 = new Employee();
        employee11.setId(EMPLOYEE11_ID);
        employee11.setName("test-employee-11");
        employee11.setPhone("566-77-11");
        employee11.setEmail("test-11@gmail.com");
        employee11.setRegistered(LocalDate.now());
        employee11.setEnabled(true);
        employee11.setDescription("test-description");
        employee11.setAddress(ADDRESSES.get(ADDRESS8_ID));
        employee11.setPassword(
                "$2y$10$52.OFyDMdNBL6pc8ud3mF.o/ZwISHJZXWWNeqizy4juQO/O6X2CPi"
        );
        employee11.setPhotoPath("path");
        employee11.setPosition(POSITIONS.get(POSITION3_ID));
        employee11.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));

        EMPLOYEES.put(EMPLOYEE11_ID, employee11);

        Employee employee12 = new Employee();
        employee12.setId(EMPLOYEE12_ID);
        employee12.setName("test-employee-12");
        employee12.setPhone("566-77-12");
        employee12.setEmail("test-12@gmail.com");
        employee12.setRegistered(LocalDate.now());
        employee12.setEnabled(true);
        employee12.setDescription("test-description");
        employee12.setAddress(ADDRESSES.get(ADDRESS8_ID));
        employee12.setPassword(
                "$2y$10$52.OFyDMdNBL6pc8ud3mF.o/ZwISHJZXWWNeqizy4juQO/O6X2CPi"
        );
        employee12.setPhotoPath("path");
        employee12.setPosition(POSITIONS.get(POSITION4_ID));
        employee12.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));

        EMPLOYEES.put(EMPLOYEE12_ID, employee12);

        Employee employee13 = new Employee();
        employee13.setId(EMPLOYEE13_ID);
        employee13.setName("test-employee-13");
        employee13.setPhone("566-77-13");
        employee13.setEmail("test-13@gmail.com");
        employee13.setRegistered(LocalDate.now());
        employee13.setEnabled(true);
        employee13.setDescription("test-description");
        employee13.setAddress(ADDRESSES.get(ADDRESS9_ID));
        employee13.setPassword(
                "$2y$10$52.OFyDMdNBL6pc8ud3mF.o/ZwISHJZXWWNeqizy4juQO/O6X2CPi"
        );
        employee13.setPhotoPath("path");
        employee13.setPosition(POSITIONS.get(POSITION5_ID));
        employee13.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));

        EMPLOYEES.put(EMPLOYEE13_ID, employee13);

        Employee employee14 = new Employee();
        employee14.setId(EMPLOYEE14_ID);
        employee14.setName("test-employee-14");
        employee14.setPhone("566-77-14");
        employee14.setEmail("test-14@gmail.com");
        employee14.setRegistered(LocalDate.now());
        employee14.setEnabled(false);
        employee14.setDescription("test-description");
        employee14.setAddress(ADDRESSES.get(ADDRESS10_ID));
        employee14.setPassword(
                "$2y$10$52.OFyDMdNBL6pc8ud3mF.o/ZwISHJZXWWNeqizy4juQO/O6X2CPi "
        );
        employee14.setPhotoPath("path");
        employee14.setPosition(POSITIONS.get(POSITION6_ID));
        employee14.setOrganization(ORGANIZATIONS.get(ORGANIZATION2_ID));

        EMPLOYEES.put(EMPLOYEE14_ID, employee14);
    }
}
