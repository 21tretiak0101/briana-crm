package by.ttre16.briana.service;

import by.ttre16.briana.entity.Client;
import by.ttre16.briana.entity.Order;
import by.ttre16.briana.repository.ClientRepository;
import by.ttre16.briana.transport.ClientTo;
import by.ttre16.briana.transport.mapper.ClientMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import static by.ttre16.briana.data.ClientTestData.CLIENT15_ID;
import static by.ttre16.briana.data.ClientTestData.CLIENTS;
import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest extends AbstractServiceTest {
    private ClientService clientService;

    @Mock
    private EventService eventService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private ClientRepository clientRepository;

    private final Client client = new Client();

    @Before
    public void setUp() {
        clientService = new ClientService(
                clientRepository,
                eventService,
                employeeService,
                clientMapper
        );
        BeanUtils.copyProperties(CLIENTS.get(CLIENT15_ID), client);
        when(clientMapper.toEntity(any())).thenReturn(client);
        when(employeeService.get(anyInt(), anyInt())).thenReturn(publisher);
        when(clientRepository.get(client.getId(), organizationId))
                .thenReturn(of(client));
    }

    @Test
    public void create() {
        when(clientRepository.save(any())).thenReturn(client);
        clientService.create(
                new ClientTo(),
                organizationId,
                publisher.getId()
        );
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    public void getDto() {
        clientService.getDto(client.getId(), organizationId);
        verify(clientMapper).toDto(client);
    }

    @Test
    public void get() {
        clientService.get(client.getId(), organizationId);
        verify(clientRepository).get(client.getId(), organizationId);
    }

    @Test
    public void update() {
        ClientTo test = new ClientTo();
        test.setId(client.getId());
        clientService.update(test, organizationId, publisher.getId());
        verify(clientMapper).copyProperties(any(Client.class), any(ClientTo.class));
        verify(clientRepository).update(any(Client.class));
    }

    @Test
    public void addOrder() {
        int initialSize = client.getOrders().size();
        clientService.addOrder(new Order(), client.getId(), organizationId);
        verify(clientRepository).update(any(Client.class));
        Assert.assertEquals(initialSize + 1, client.getOrders().size());
    }
}
