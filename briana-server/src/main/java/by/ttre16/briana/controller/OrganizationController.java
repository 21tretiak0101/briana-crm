package by.ttre16.briana.controller;

import by.ttre16.briana.annotation.Authenticated;
import by.ttre16.briana.security.AuthenticatedEmployee;
import by.ttre16.briana.service.OrganizationService;
import by.ttre16.briana.transport.OrganizationTo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.ttre16.briana.controller.ApiMapping.ORGANIZATION_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ORGANIZATION_REST_URL)
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('organization:write')")
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)


    public ResponseEntity<OrganizationTo> get(
            @Authenticated AuthenticatedEmployee authenticated) {
        return ResponseEntity.ok()
                .body(
                        organizationService.getDto(
                                authenticated.getOrganizationId()
                        )
                );
    }
}
