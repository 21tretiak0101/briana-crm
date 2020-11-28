package by.ttre16.briana.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.lang.String.format;

public class EmployeeNotFoundException extends UsernameNotFoundException {
    public EmployeeNotFoundException(String email) {
        super(format("Employee with email '%s' not found.", email));
    }
}
