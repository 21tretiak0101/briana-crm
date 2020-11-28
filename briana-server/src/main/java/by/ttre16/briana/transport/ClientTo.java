package by.ttre16.briana.transport;

import by.ttre16.briana.entity.Order;
import by.ttre16.briana.transport.base.UserTo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ClientTo extends UserTo {
    private LocalDate created;
    private List<Order> orders;
}
