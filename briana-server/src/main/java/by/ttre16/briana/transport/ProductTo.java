package by.ttre16.briana.transport;

import by.ttre16.briana.transport.base.DescribedTo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTo extends DescribedTo {
    private CategoryTo category;
    private Double price;
}
