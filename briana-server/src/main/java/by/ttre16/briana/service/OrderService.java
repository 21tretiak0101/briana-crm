package by.ttre16.briana.service;

import by.ttre16.briana.entity.Event;
import by.ttre16.briana.entity.EventType;
import by.ttre16.briana.entity.MessageTopic;
import by.ttre16.briana.entity.Order;
import by.ttre16.briana.repository.OrderRepository;
import by.ttre16.briana.transport.OrderTo;
import by.ttre16.briana.transport.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final EventService eventService;
    private final EmployeeService employeeService;
    private final OrderMapper orderMapper;

    @Transactional(readOnly = true)
    public List<OrderTo> getAllByClientId(
            Integer clientId, Integer organizationId) {
        return orderRepository.getAllByClientId(clientId, organizationId)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrderTo create(
            OrderTo orderTo,
            Integer clientId,
            Integer organizationId,
            Integer publisherId) {
        Order savedOrder = orderRepository.save(
                orderMapper.toEntity(orderTo)
        );
        clientService.addOrder(savedOrder, clientId, organizationId);
        eventService.push(
                new Event(
                        employeeService.get(publisherId, organizationId),
                        EventType.ADD,
                        MessageTopic.ORDER,
                        savedOrder.getId()
                )
        );
        return orderMapper.toDto(savedOrder);
    }
}
