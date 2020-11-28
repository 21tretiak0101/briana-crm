package by.ttre16.briana.service;

import by.ttre16.briana.entity.Client;
import by.ttre16.briana.entity.Order;
import by.ttre16.briana.repository.OrderRepository;
import by.ttre16.briana.transport.OrderTo;
import by.ttre16.briana.transport.mapper.OrderMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.List;

import static by.ttre16.briana.data.ClientTestData.CLIENT15_ID;
import static by.ttre16.briana.data.ClientTestData.CLIENTS;
import static by.ttre16.briana.data.OrderTestData.ORDER24_ID;
import static by.ttre16.briana.data.OrderTestData.ORDERS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest extends AbstractServiceTest {
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private EventService eventService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private OrderMapper orderMapper;

    private Order order;

    private Client client;

    @Before
    public void setUp() {
        orderService = new OrderService(
                orderRepository,
                clientService,
                eventService,
                employeeService,
                orderMapper
        );
        order = new Order();
        BeanUtils.copyProperties(ORDERS.get(ORDER24_ID), order);
        client = CLIENTS.get(CLIENT15_ID);
        when(employeeService.get(anyInt(), anyInt())).thenReturn(publisher);
    }

    @Test
    public void getAllByClientId() {
        when(orderRepository.getAllByClientId(client.getId(), organizationId))
                .thenReturn(client.getOrders());
        List<OrderTo> orders = orderService.getAllByClientId(
                client.getId(),
                organizationId
        );
        Assert.assertEquals(orders.size(), client.getOrders().size());
    }

    @Test
    public void create() {
        when(orderRepository.save(any())).thenReturn(order);
        orderService.create(
                new OrderTo(),
                client.getId(),
                organizationId,
                publisher.getId()
        );
        verify(clientService).addOrder(order, client.getId(), organizationId);
    }
}
