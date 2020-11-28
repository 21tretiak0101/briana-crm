package by.ttre16.briana.security;

import by.ttre16.briana.entity.Employee;
import by.ttre16.briana.exception.EmployeeNotFoundException;
import by.ttre16.briana.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeDetailsService implements UserDetailsService {
    private final EmployeeRepository userRepository;

    @Override
    public EmployeeDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Employee user = userRepository.getByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException(email));
        return new EmployeeDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                user.getPosition().getPermissions()
        );
    }
}
