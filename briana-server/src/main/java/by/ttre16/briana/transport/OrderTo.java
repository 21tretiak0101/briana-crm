package by.ttre16.briana.transport;

import by.ttre16.briana.transport.base.BaseTo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderTo extends BaseTo {
    private List<ProductTo> products;
    private Integer clientId;
}
