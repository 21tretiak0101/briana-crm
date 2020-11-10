package by.ttre16.briana.service;

import by.ttre16.briana.AbstractTest;
import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.entity.Employee;
import by.ttre16.briana.exception.EntityNotFoundException;
import by.ttre16.briana.repository.EmployeeRepository;
import by.ttre16.briana.transport.EmployeeTo;
import by.ttre16.briana.transport.mapper.EmployeeMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static by.ttre16.briana.data.EmployeeTestData.*;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest extends AbstractTest {
    private EmployeeService employeeService;

    @Mock
    private ImageService imageService;

    @Mock
    private EventService eventService;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    private final Employee employee = EMPLOYEES.get(EMPLOYEE14_ID);

    private final Employee publisher = EMPLOYEES.get(EMPLOYEE13_ID);

    private final Integer organizationId = publisher.getOrganization().getId();

    @Before
    public void setupMock() {
        employeeService = new EmployeeService(
                imageService,
                employeeRepository,
                eventService,
                employeeMapper
        );
        when(employeeMapper.toEntity(any())).thenReturn(employee);

        when(employeeRepository.get(anyInt(), anyInt())).then(args -> {
            Integer organizationId = args.getArgument(1);
            return organizationId.equals(employee.getOrganization().getId())
                    ? Optional.of(employee)
                    : Optional.empty();
        });
    }

    @Test
    public void downloadPhoto() {
        byte[] expectedBytes = new byte[]{10, 20, 30};
        when(imageService.download(anyString())).thenReturn(expectedBytes);
        byte[] downloadedBytes = employeeService.downloadPhoto(
                employee.getId(),
                organizationId
        );
        assertArrayEquals(expectedBytes, downloadedBytes);
    }

    @Test
    public void create() {
        when(employeeRepository.save(any())).thenReturn(employee);
        employeeService.create(
                new EmployeeTo(),
                publisher.getOrganization().getId(),
                new MockMultipartFile("test", new byte[]{}),
                publisher.getId()
        );
        verify(employeeRepository).save(any());
        verify(imageService).upload(any(), eq("employee:" + employee.getEmail()));
    }

    @Test
    public void get() {
        RecursiveAssert.assertMatch(
                employee,
                employeeService.get(
                        employee.getId(),
                        organizationId
                )
        );
    }

    @Test
    public void getWithMissingOrganizationId() {
        Assert.assertThrows(EntityNotFoundException.class, () -> {
            employeeService.get(employee.getId(), -1);
        });
    }

    @Test
    public void getWithMissingEmployeeId() {
        Assert.assertThrows(EntityNotFoundException.class, () -> {
            employeeService.get(-1, organizationId);
        });
    }

    @Test
    public void update() {
        EmployeeTo employeeTo = new EmployeeTo();
        employeeTo.setId(1);
        when(employeeRepository.update(any())).thenReturn(employee);
        employeeService.update(employeeTo, organizationId, publisher.getId());
        verify(employeeMapper).copyProperties(employee, employeeTo);
        verify(employeeRepository).update(any());
    }
}
