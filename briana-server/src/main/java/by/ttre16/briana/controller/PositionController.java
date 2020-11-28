package by.ttre16.briana.controller;

import by.ttre16.briana.annotation.Authenticated;
import by.ttre16.briana.security.AuthenticatedEmployee;
import by.ttre16.briana.service.PositionService;
import by.ttre16.briana.transport.PositionTo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static by.ttre16.briana.controller.ApiMapping.POSITION_REST_URL;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(POSITION_REST_URL)
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;
    private static final Logger log = getLogger(PositionController.class);

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('position:write')")
    public ResponseEntity<PositionTo> create(
            @RequestBody PositionTo positionTo,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("POST request: create()");
        PositionTo created = positionService.create(
                positionTo,
                authenticated.getOrganizationId(),
                authenticated.getId()
        );
        URI resourceUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(POSITION_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity
                .created(resourceUri)
                .body(created);
    }
}
