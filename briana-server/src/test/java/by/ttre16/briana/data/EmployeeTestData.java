package by.ttre16.briana.data;

import by.ttre16.briana.entity.Address;
import by.ttre16.briana.entity.Employee;
import by.ttre16.briana.entity.Position;
import by.ttre16.briana.transport.AddressTo;
import by.ttre16.briana.transport.EmployeeTo;
import by.ttre16.briana.transport.PositionTo;

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

    public static EmployeeTo getNewTo() {
        EmployeeTo employeeTo = new EmployeeTo();

        Position testPosition = POSITIONS.get(POSITION3_ID);
        PositionTo positionTo = new PositionTo();

        positionTo.setId(testPosition.getId());
        positionTo.setName(testPosition.getName());
        positionTo.setDescription(testPosition.getDescription());
        positionTo.setPermissions(positionTo.getPermissions());

        AddressTo addressTo = new AddressTo();
        Address testAddress = ADDRESSES.get(ADDRESS7_ID);

        addressTo.setCountry(testAddress.getCountry());
        addressTo.setCity(testAddress.getCity());
        addressTo.setPostcode(testAddress.getPostcode());

        employeeTo.setName("new:name");
        employeeTo.setEmail("new.employee@gmail.com");
        employeeTo.setPhone("332-22-12");
        employeeTo.setPassword("new-password");
        employeeTo.setPosition(positionTo);
        employeeTo.setAddress(addressTo);
        employeeTo.setDescription("new:employee:description");
        employeeTo.setOrganizationId(testPosition.getOrganization().getId());
        return employeeTo;
    }

    public static Employee getNewFrom(EmployeeTo employeeTo) {
        Employee employee = new Employee();
        Position employeePosition = POSITIONS.get(POSITION3_ID);

        employee.setPosition(employeePosition);
        employee.setOrganization(employeePosition.getOrganization());

        employee.setName(employeeTo.getName());
        employee.setPhone(employeeTo.getPhone());
        employee.setEmail(employeeTo.getEmail());
        employee.setRegistered(LocalDate.now());
        employee.setEnabled(true);
        employee.setDescription(employeeTo.getDescription());
        employee.setAddress(ADDRESSES.get(ADDRESS7_ID));
        employee.setPassword(
                "$2y$10$52.OFyDMdNBL6pc8ud3mF.o/ZwISHJZXWWNeqizy4juQO/O6X2CPi"
        );

        return employee;
    }

    public static EmployeeTo getFrom(Employee employee) {
        EmployeeTo employeeTo = new EmployeeTo();

        Position position = employee.getPosition();
        PositionTo positionTo = new PositionTo();

        positionTo.setId(position.getId());
        positionTo.setName(position.getName());
        positionTo.setDescription(position.getDescription());
        positionTo.setPermissions(positionTo.getPermissions());

        AddressTo addressTo = new AddressTo();
        Address address = employee.getAddress();

        addressTo.setCountry(address.getCountry());
        addressTo.setCity(address.getCity());
        addressTo.setPostcode(address.getPostcode());

        employeeTo.setName(employee.getName());
        employeeTo.setEmail(employee.getEmail());
        employeeTo.setPhone(employeeTo.getPhone());
        employeeTo.setPassword(employee.getPassword());
        employeeTo.setPosition(positionTo);
        employeeTo.setAddress(addressTo);
        employeeTo.setDescription(employee.getDescription());
        employeeTo.setOrganizationId(employee.getOrganization().getId());

        return employeeTo;
    }
}
