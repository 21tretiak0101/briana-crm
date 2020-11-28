package by.ttre16.briana.security.jwt.filter;

import by.ttre16.briana.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String token = jwtService.parseToken(request);
        if (jwtService.isValidToken(token)) {
            SecurityContextHolder.getContext()
                    .setAuthentication(
                            jwtService.getAuthentication(token)
                    );
        }
        filterChain.doFilter(request, response);
    }
}
