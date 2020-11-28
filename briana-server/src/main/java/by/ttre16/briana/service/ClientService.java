package by.ttre16.briana.service;

import by.ttre16.briana.entity.*;
import by.ttre16.briana.repository.ClientRepository;
import by.ttre16.briana.transport.ClientTo;
import by.ttre16.briana.transport.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static by.ttre16.briana.exception.EntityNotFoundException.with;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final EventService eventService;
    private final EmployeeService employeeService;
    private final ClientMapper clientMapper;

    public ClientTo create(
            ClientTo clientTo,
            Integer organizationId,
            Integer publisherId) {
        Client saved = clientRepository.save(
                clientMapper.toEntity(clientTo)
        );
        Employee publisher = employeeService.get(publisherId, organizationId);
        eventService.push(
                new Event(
                        publisher,
                        EventType.ADD,
                        MessageTopic.CLIENT,
                        saved.getId()
                )
        );
        return clientMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public ClientTo getDto(Integer id, Integer organizationId) {
        return clientMapper.toDto(get(id, organizationId));
    }

    @Transactional(readOnly = true)
    protected Client get(Integer id, Integer organizationId) {
        return clientRepository.get(id, organizationId)
                .orElseThrow(() -> with(Client.class, id));
    }

    public void update(
            ClientTo client,
            Integer organizationId,
            Integer publisherId) {
        Client old = get(client.getId(), organizationId);
        clientMapper.copyProperties(old, client);
        clientRepository.update(old);
        eventService.push(
                new Event(
                        employeeService.get(publisherId, organizationId),
                        EventType.UPDATE,
                        MessageTopic.CLIENT,
                        old.getId()
                )
        );
    }

    public void addOrder(
            Order order,
            Integer clientId,
            Integer organizationId) {
        Client client = get(clientId, organizationId);
        List<Order> updatedOrders = new ArrayList<>(client.getOrders());
        updatedOrders.add(order);
        client.setOrders(updatedOrders);
        clientRepository.update(client);
    }
}
