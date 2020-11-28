package by.ttre16.briana.security.jwt.filter;

import by.ttre16.briana.security.EmployeeDetails;
import by.ttre16.briana.security.jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtAuthenticationFilter(
            String filterProcessesUrl,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        setFilterProcessesUrl(filterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            EmployeeDetails securityUserDetails =
                    new ObjectMapper()
                            .readValue(request.getInputStream(),
                                    EmployeeDetails.class);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            securityUserDetails,
                            securityUserDetails.getPassword()
                    );

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) {
        EmployeeDetails userDetails = (EmployeeDetails) authResult.getPrincipal();

        String token = jwtService.createToken(
                userDetails.getId(),
                userDetails.getUsername(),
                authResult.getAuthorities()
        );

        response.addHeader(
                jwtService.getAuthorizationHeader(),
                jwtService.createBearerToken(token)
        );
    }
}
