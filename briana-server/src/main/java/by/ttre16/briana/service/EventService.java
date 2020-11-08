package by.ttre16.briana.service;

import by.ttre16.briana.entity.Event;
import by.ttre16.briana.repository.EventRepository;
import by.ttre16.briana.transport.EventTo;
import by.ttre16.briana.transport.mapper.EventMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class EventService {
    private EventRepository eventRepository;
    private EventMapper eventMapper;
    private static final Logger log = getLogger(EventService.class);

    @Transactional(propagation = Propagation.SUPPORTS)
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public EventTo push(Event event) {
        Event savedEvent = eventRepository.save(event);
        log.info("pushing event: {}", savedEvent.getMessage());
        return eventMapper.toDto(savedEvent);
    }
}
