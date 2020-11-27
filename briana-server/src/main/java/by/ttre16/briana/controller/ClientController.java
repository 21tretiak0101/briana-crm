package by.ttre16.briana.controller;

import by.ttre16.briana.annotation.Authenticated;
import by.ttre16.briana.security.AuthenticatedEmployee;
import by.ttre16.briana.service.ClientService;
import by.ttre16.briana.transport.ClientTo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static by.ttre16.briana.controller.ApiMapping.CLIENT_REST_URL;
import static by.ttre16.briana.controller.ApiMapping.EMPLOYEE_REST_URL;
import static by.ttre16.briana.controller.ApiValidation.assureThatIdConsistent;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(CLIENT_REST_URL)
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('client:write')")
public class ClientController {
    private final ClientService clientService;
    private static final Logger log = getLogger(ClientController.class);

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientTo> create(
            @RequestBody ClientTo clientTo,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("POST request: create()");
        ClientTo created = clientService.create(
                clientTo,
                authenticated.getOrganizationId(),
                authenticated.getId()
        );
        URI resourceUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(EMPLOYEE_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity
                .created(resourceUri)
                .body(created);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable Integer id,
            @RequestBody ClientTo clientTo,
            @Authenticated AuthenticatedEmployee authenticated) {
        assureThatIdConsistent(clientTo, id);
        clientService.update(
                clientTo,
                authenticated.getId(),
                authenticated.getOrganizationId()
        );
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('client:read')")
    public ResponseEntity<ClientTo> get(
            @PathVariable Integer id,
            @Authenticated AuthenticatedEmployee authenticated) {
        return ResponseEntity.ok()
                .body(
                        clientService.getDto(
                                id,
                                authenticated.getOrganizationId()
                        )
                );
    }
}
