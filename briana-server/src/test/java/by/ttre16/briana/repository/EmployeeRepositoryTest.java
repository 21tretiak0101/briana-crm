package by.ttre16.briana.repository;

import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.data.EmployeeTestData;
import by.ttre16.briana.entity.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static by.ttre16.briana.data.EmployeeTestData.*;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATION1_ID;

public class EmployeeRepositoryTest extends AbstractRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Transactional
    public void save() {
        Employee employee = EmployeeTestData.getNew();

        Employee saved = employeeRepository.save(employee);

        Assert.assertNotNull(saved);
        RecursiveAssert.assertMatch(
                saved,
                entityManager.find(Employee.class, saved.getId())
        );
    }

    @Test
    @Transactional
    public void update() {
        Employee employee = new Employee();
        BeanUtils.copyProperties(
            EMPLOYEES.get(EMPLOYEE14_ID),
                employee
        );

        employee.setName("updated:employee");
        Employee updated = employeeRepository.update(employee);

        RecursiveAssert.assertMatch(
                updated,
                entityManager.find(Employee.class, updated.getId())
        );
    }

    @Test
    @Transactional(readOnly = true)
    public void getByEmail() {
        Employee expected = EMPLOYEES.get(EMPLOYEE12_ID);

        employeeRepository.getByEmail(
                expected.getEmail()
        ).ifPresent(employee -> RecursiveAssert.assertMatch(
                expected,
                employee
        ));
    }

    @Test
    @Transactional(readOnly = true)
    public void getAll() {
        List<Employee> expected = EMPLOYEES.values().stream()
                .filter(employee ->
                        ORGANIZATION1_ID.equals(
                                employee.getOrganization().getId()
                        )
                )
                .collect(Collectors.toList());

        RecursiveAssert.assertMatch(
                expected,
                employeeRepository.getAll(ORGANIZATION1_ID)
        );
    }

    @Test
    @Transactional(readOnly = true)
    public void get() {
        Employee expected = EMPLOYEES.get(EMPLOYEE13_ID);
        Employee actual = employeeRepository.get(
                expected.getId(),
                expected.getOrganization().getId()
        )
                .orElseThrow(AssertionError::new);

        RecursiveAssert.assertMatch(
                expected,
                actual
        );
    }
}
