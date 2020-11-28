package by.ttre16.briana.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticatedEmployee {
    private Integer id;
    private String email;
    private Integer organizationId;
}
