package by.ttre16.briana.transport;

import by.ttre16.briana.transport.base.HasPassword;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationOwner implements HasPassword {
    private String name;
    private String phone;
    private String email;
    private String password;
    private String photoPath;
    private String organizationName;
    private String organizationCurrency;
    private String organizationDescription;
}
