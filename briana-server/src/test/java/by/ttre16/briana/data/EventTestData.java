package by.ttre16.briana.data;

import by.ttre16.briana.entity.Event;
import by.ttre16.briana.entity.EventType;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static by.ttre16.briana.data.EmployeeTestData.*;
import static by.ttre16.briana.data.OrganizationTestData.*;

public class EventTestData {
    public static final Map<Integer, Event> EVENTS = new HashMap<>();
    public static final Integer EVENT26_ID = 26;
    public static final Integer EVENT27_ID = 27;
    public static final Integer EVENT28_ID = 28;

    static {
        Event event26 = new Event();
        event26.setId(EVENT26_ID);
        event26.setMessage("product:21");
        event26.setType(EventType.ADD);
        event26.setPublished(LocalDateTime.now());
        event26.setPublisher(EMPLOYEES.get(EMPLOYEE13_ID));
        event26.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));

        EVENTS.put(EVENT26_ID, event26);

        Event event27 = new Event();
        event27.setId(EVENT27_ID);
        event27.setMessage("employee:13");
        event27.setType(EventType.UPDATE);
        event27.setPublished(LocalDateTime.now());
        event27.setPublisher(EMPLOYEES.get(EMPLOYEE13_ID));
        event27.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));

        EVENTS.put(EVENT27_ID, event27);

        Event event28 = new Event();
        event28.setId(EVENT26_ID);
        event28.setMessage("product:22");
        event28.setType(EventType.DELETE);
        event28.setPublished(LocalDateTime.now());
        event28.setPublisher(EMPLOYEES.get(EMPLOYEE14_ID));
        event28.setOrganization(ORGANIZATIONS.get(ORGANIZATION2_ID));

        EVENTS.put(EVENT28_ID, event28);
    }
}
