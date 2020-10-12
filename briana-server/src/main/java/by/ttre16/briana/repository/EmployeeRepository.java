package by.ttre16.briana.repository;

import by.ttre16.briana.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * The repository class for {@link Employee} entity.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see Repository
 * @see EntityManager
 */

@Repository
public class EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Employee save(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    public Employee update(Employee employee) {
        return entityManager.merge(employee);
    }

    public Optional<Employee> getByEmail(String email, Integer organizationId) {
        return entityManager.createNamedQuery(
                Employee.GET_BY_EMAIL,
                Employee.class
        )
                .setParameter("email", email)
                .setParameter("organizationId", organizationId)
                .getResultList().stream()
                .findFirst();
    }

    public List<Employee> getAll(Integer organizationId) {
        return entityManager.createNamedQuery(
                Employee.GET_ALL,
                Employee.class
        )
                .setParameter("organizationId", organizationId)
                .getResultList();
    }
}
