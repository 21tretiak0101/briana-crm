package by.ttre16.briana.repository;

import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.entity.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static by.ttre16.briana.data.AddressTestData.ADDRESS8_ID;
import static by.ttre16.briana.data.AddressTestData.ADDRESSES;
import static by.ttre16.briana.data.EmployeeTestData.*;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATION1_ID;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATIONS;
import static by.ttre16.briana.data.PositionTestData.POSITION3_ID;
import static by.ttre16.briana.data.PositionTestData.POSITIONS;

public class EmployeeRepositoryTest extends AbstractRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Transactional
    public void save() {
        Employee employee = new Employee();
        employee.setName("test:employee");
        employee.setPhone("232-322-123");
        employee.setEmail("test@gmail.com");
        employee.setRegistered(LocalDate.now());
        employee.setEnabled(true);
        employee.setDescription("test:description");
        employee.setAddress(ADDRESSES.get(ADDRESS8_ID));
        employee.setPassword("secret:password");
        employee.setPhotoPath("path/to/photo");
        employee.setPosition(POSITIONS.get(POSITION3_ID));
        employee.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));

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
