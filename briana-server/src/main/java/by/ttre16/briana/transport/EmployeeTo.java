package by.ttre16.briana.transport;

import by.ttre16.briana.transport.base.HasPassword;
import by.ttre16.briana.transport.base.UserTo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeTo extends UserTo implements HasPassword {
    private PositionTo position;
    private Boolean enabled;
    private String password;
}
