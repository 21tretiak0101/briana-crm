package by.ttre16.briana.service;

import by.ttre16.briana.entity.Employee;
import org.junit.Before;
import org.springframework.beans.BeanUtils;

import static by.ttre16.briana.data.EmployeeTestData.EMPLOYEE13_ID;
import static by.ttre16.briana.data.EmployeeTestData.EMPLOYEES;

public abstract class AbstractServiceTest {
    protected Employee publisher;

    protected Integer organizationId;

    @Before
    public void init() {
        publisher = new Employee();
        BeanUtils.copyProperties(EMPLOYEES.get(EMPLOYEE13_ID), publisher);
        organizationId = publisher.getOrganization().getId();
    }
}
