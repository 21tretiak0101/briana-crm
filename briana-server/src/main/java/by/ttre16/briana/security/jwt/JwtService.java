package by.ttre16.briana.security.jwt;

import by.ttre16.briana.security.AuthenticatedEmployee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class JwtService {
    @Value("${jwt.private-key}")
    private String key;

    @Value("${jwt.authorizationHeader}")
    private String authorizationHeader;

    @Value("${jwt.validity}")
    private Integer validity;

    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @PostConstruct
    public void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public String parseToken(HttpServletRequest request) {
        return request.getHeader(authorizationHeader);
    }

    public boolean isValidToken(String token) {
        return !isEmpty(token)
                && token.startsWith(tokenPrefix)
                && resolveToken(token).getExpiration().after(new Date());
    }

    private Set<SimpleGrantedAuthority> getAuthorities(String token) {
        return parseAuthorities(token).stream()
                .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                .collect(Collectors.toSet());
    }

    private Claims resolveToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseClaimsJws(token.replace(tokenPrefix, ""))
                .getBody();
    }

    public String createToken(
            Integer id,
            String email,
            Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
                .setSubject(email)
                .claim("employeeId", id)
                .claim("organizationId", id)
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(valueOf(now().plusDays(validity)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
    }

    public String getAuthorizationHeader() {
        return authorizationHeader;
    }

    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(
                new AuthenticatedEmployee(
                        parseInt(token, "employeeId"),
                        parseEmail(token),
                        parseInt(token, "organizationId")
                ),
                null,
                getAuthorities(token)
        );
    }

    public String createBearerToken(String token) {
        return tokenPrefix + token;
    }

    private String parseEmail(String email) {
        return resolveToken(email).getSubject();
    }

    private Integer parseInt(String token, String claimName) {
        return resolveToken(token).get(claimName, Integer.TYPE);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, String>> parseAuthorities(String token) {
        return (List<Map<String, String>>) resolveToken(token).get("authorities");
    }
}
