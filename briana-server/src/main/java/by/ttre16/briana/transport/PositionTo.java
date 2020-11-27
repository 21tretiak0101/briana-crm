package by.ttre16.briana.transport;

import by.ttre16.briana.transport.base.DescribedTo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PositionTo extends DescribedTo {
    private List<String> permissions;
}
