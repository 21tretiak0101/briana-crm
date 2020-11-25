package by.ttre16.briana.service;

import by.ttre16.briana.entity.Position;
import by.ttre16.briana.repository.PositionRepository;
import by.ttre16.briana.transport.PositionTo;
import by.ttre16.briana.transport.mapper.PositionMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

import static by.ttre16.briana.data.PositionTestData.POSITION4_ID;
import static by.ttre16.briana.data.PositionTestData.POSITIONS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PositionServiceTest extends AbstractServiceTest {
    private PositionService positionService;

    @Mock
    private EventService eventService;

    @Mock
    private PositionMapper positionMapper;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private PositionRepository positionRepository;

    private final Position position = new Position();

    @Before
    public void setUp() {
        positionService = new PositionService(
                positionRepository,
                employeeService,
                positionMapper,
                eventService
        );
        BeanUtils.copyProperties(POSITIONS.get(POSITION4_ID), position);
        when(positionRepository.get(anyInt(), anyInt()))
                .thenReturn(Optional.of(position));
        when(employeeService.get(anyInt(), anyInt())).thenReturn(publisher);
        when(positionMapper.toEntity(any())).thenReturn(position);
    }

    @Test
    public void create() {
        when(positionRepository.save(any())).thenReturn(position);
        positionService.create(
                new PositionTo(),
                organizationId,
                publisher.getId()
        );
        verify(positionRepository).save(any());
    }

    @Test
    public void update() {
        when(positionRepository.update(any())).thenReturn(position);
        PositionTo test = new PositionTo();
        test.setId(position.getId());
        positionService.update(test, organizationId, publisher.getId());
        verify(positionMapper).copyProperties(any(), any());
        verify(positionRepository).update(any());
    }

    @Test
    public void get() {
        positionService.get(position.getId(), organizationId);
        verify(positionRepository).get(position.getId(), organizationId);
    }
}
