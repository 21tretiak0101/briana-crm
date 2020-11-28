package by.ttre16.briana.transport;

import by.ttre16.briana.transport.base.BaseTo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventTo extends BaseTo {
    private String type;
    private String message;
    private Integer publisherId;
    private LocalDateTime published;
}
