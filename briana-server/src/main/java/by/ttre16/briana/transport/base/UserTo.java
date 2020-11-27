package by.ttre16.briana.transport.base;

import by.ttre16.briana.transport.AddressTo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserTo extends DescribedTo {
    private String phone;
    private String email;
    private AddressTo address;
}
