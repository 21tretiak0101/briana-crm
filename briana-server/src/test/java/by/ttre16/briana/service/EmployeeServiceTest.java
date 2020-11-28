package by.ttre16.briana.service;

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
import org.springframework.beans.BeanUtils;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.Optional;

import static by.ttre16.briana.data.EmployeeTestData.EMPLOYEE14_ID;
import static by.ttre16.briana.data.EmployeeTestData.EMPLOYEES;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest extends AbstractServiceTest {
    private EmployeeService employeeService;

    @Mock
    private ImageService imageService;

    @Mock
    private EventService eventService;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    private final Employee employee = new Employee();

    @Before
    public void setupMocks() {
        employeeService = new EmployeeService(
                imageService,
                employeeRepository,
                eventService,
                employeeMapper
        );

        BeanUtils.copyProperties(EMPLOYEES.get(EMPLOYEE14_ID), this.employee);

        when(employeeMapper.toEntity(any())).thenReturn(employee);

        when(employeeRepository.get(anyInt(), anyInt())).then(args -> {
            Integer employeeId = args.getArgument(0);
            if (!List.of(publisher.getId(), employee.getId()).contains(employeeId)) {
                return Optional.empty();
            }
            Integer organizationId = args.getArgument(1);
            return organizationId.equals(this.organizationId)
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
                organizationId,
                new MockMultipartFile("test", new byte[]{}),
                publisher.getId()
        );
        verify(employeeRepository).save(any());
        verify(imageService).upload(
                any(),
                eq("employee:" + employee.getEmail())
        );
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
        employeeTo.setId(employee.getId());
        when(employeeRepository.update(any())).thenReturn(employee);
        employeeService.update(employeeTo, organizationId, publisher.getId());
        verify(employeeMapper).copyProperties(employee, employeeTo);
        verify(employeeRepository).update(any());
    }

    @Test
    public void updatePhoto() {
        employeeService.updatePhoto(
                employee.getId(),
                organizationId,
                new MockMultipartFile("test", new byte[]{}),
                publisher.getId()
        );
        verify(imageService).delete(anyString());
        verify(imageService).upload(
                any(),
                eq("employee:" + employee.getEmail())
        );
    }
}
