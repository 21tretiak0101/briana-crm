package by.ttre16.briana.service;

import by.ttre16.briana.entity.Event;
import by.ttre16.briana.entity.EventType;
import by.ttre16.briana.entity.MessageTopic;
import by.ttre16.briana.entity.Position;
import by.ttre16.briana.exception.EntityNotFoundException;
import by.ttre16.briana.repository.PositionRepository;
import by.ttre16.briana.transport.PositionTo;
import by.ttre16.briana.transport.mapper.PositionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PositionService {
    private final PositionRepository positionRepository;
    private final EmployeeService employeeService;
    private final PositionMapper positionMapper;
    private final EventService eventService;

    public PositionTo create(
            PositionTo position,
            Integer organizationId,
            Integer publisherId) {
        Position savedPosition = positionRepository.save(
                positionMapper.toEntity(position)
        );
        eventService.push(
                new Event(
                        employeeService.get(publisherId, organizationId),
                        EventType.ADD,
                        MessageTopic.POSITION,
                        savedPosition.getId()
                )
        );
        return positionMapper.toDto(savedPosition);
    }

    public void update(
            PositionTo positionTo,
            Integer organizationId,
            Integer publisherId) {
        Position old = get(positionTo.getId(), organizationId);
        positionMapper.copyProperties(old, positionTo);
        Position updated = positionRepository.update(old);
        eventService.push(
                new Event(
                        employeeService.get(publisherId, organizationId),
                        EventType.UPDATE,
                        MessageTopic.POSITION,
                        updated.getId()
                )
        );
    }

    @Transactional(readOnly = true)
    public Position get(Integer id, Integer organizationId) {
        return positionRepository.get(id, organizationId)
                .orElseThrow(() -> EntityNotFoundException.with(
                        Position.class, id
                ));
    }
}
