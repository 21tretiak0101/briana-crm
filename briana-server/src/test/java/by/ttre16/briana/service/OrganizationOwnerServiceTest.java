package by.ttre16.briana.service;

import by.ttre16.briana.repository.EmployeeRepository;
import by.ttre16.briana.repository.OrganizationRepository;
import by.ttre16.briana.repository.PositionRepository;
import by.ttre16.briana.transport.OrganizationOwner;
import by.ttre16.briana.transport.mapper.EmployeeMapper;
import by.ttre16.briana.transport.mapper.OrganizationOwnerMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationOwnerServiceTest extends AbstractServiceTest {
    private OrganizationOwnerService organizationOwnerService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private OrganizationOwnerMapper organizationOwnerMapper;

    @Mock
    private EmployeeMapper employeeMapper;

    @Before
    public void setUp() {
        organizationOwnerService = new OrganizationOwnerService(
                employeeRepository,
                positionRepository,
                organizationRepository,
                organizationOwnerMapper,
                employeeMapper
        );
    }

    @Test
    public void create() {
        OrganizationOwner organizationOwner = new OrganizationOwner();
        when(organizationOwnerMapper.getOwner(organizationOwner))
                .thenReturn(publisher);
        when(organizationOwnerMapper.getOrganization(organizationOwner))
                .thenReturn(publisher.getOrganization());
        when(organizationRepository.save(any()))
                .thenReturn(publisher.getOrganization());
        organizationOwnerService.create(organizationOwner);
        verify(organizationRepository).save(publisher.getOrganization());
        verify(positionRepository).save(any());
        verify(employeeRepository).save(publisher);
    }
}
