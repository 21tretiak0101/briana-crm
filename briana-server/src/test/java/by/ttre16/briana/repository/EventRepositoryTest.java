package by.ttre16.briana.repository;

import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.entity.Employee;
import by.ttre16.briana.entity.Event;
import by.ttre16.briana.entity.EventType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static by.ttre16.briana.data.EmployeeTestData.EMPLOYEE11_ID;
import static by.ttre16.briana.data.EmployeeTestData.EMPLOYEES;
import static by.ttre16.briana.data.EventTestData.EVENT26_ID;
import static by.ttre16.briana.data.EventTestData.EVENTS;

public class EventRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void save() {
        Event event = new Event();
        Employee publisher = EMPLOYEES.get(EMPLOYEE11_ID);
        event.setPublisher(publisher);
        event.setOrganization(publisher.getOrganization());
        event.setPublished(LocalDateTime.now());
        event.setType(EventType.ADD);
        event.setMessage("product:33");
        Event saved = eventRepository.save(event);

        Assert.assertNotNull(saved);
        RecursiveAssert.assertMatch(
                saved,
                entityManager.find(Event.class, saved.getId())
        );
    }

    @Test
    @Transactional(readOnly = true)
    public void get() {
        Event expected = EVENTS.get(EVENT26_ID);
        eventRepository.get(
                expected.getId(),
                expected.getOrganization().getId()
        ).ifPresent(event -> RecursiveAssert.assertMatch(
                expected,
                event,
                "published"
        ));
    }
}
