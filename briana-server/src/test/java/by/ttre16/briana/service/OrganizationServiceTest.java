package by.ttre16.briana.service;

import by.ttre16.briana.entity.Organization;
import by.ttre16.briana.repository.OrganizationRepository;
import by.ttre16.briana.transport.mapper.OrganizationMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceTest extends AbstractServiceTest {
    private OrganizationService organizationService;

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private OrganizationMapper organizationMapper;

    private Organization organization;

    @Before
    public void setUp() {
        organizationService = new OrganizationService(
                organizationRepository,
                organizationMapper
        );
        organization = new Organization();
        when(organizationRepository.get(organizationId))
                .thenReturn(Optional.of(organization));
    }

    @Test
    public void get() {
        organizationService.get(organizationId);
        verify(organizationRepository).get(organizationId);
    }

    @Test
    public void getDto() {
        organizationService.getDto(organizationId);
        verify(organizationMapper).toDto(organization);
    }
}
