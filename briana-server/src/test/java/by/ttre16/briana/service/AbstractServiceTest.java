package by.ttre16.briana.service;

import by.ttre16.briana.entity.Employee;
import org.junit.Before;

import static by.ttre16.briana.data.EmployeeTestData.EMPLOYEE13_ID;
import static by.ttre16.briana.data.EmployeeTestData.EMPLOYEES;

public abstract class AbstractServiceTest {
    protected Employee publisher;

    protected Integer organizationId;

    @Before
    public void init() {
        publisher = EMPLOYEES.get(EMPLOYEE13_ID);
        organizationId = publisher.getOrganization().getId();
    }
}
