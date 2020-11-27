package by.ttre16.briana.controller;

import by.ttre16.briana.annotation.Authenticated;
import by.ttre16.briana.security.AuthenticatedEmployee;
import by.ttre16.briana.service.OrderService;
import by.ttre16.briana.transport.OrderTo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static by.ttre16.briana.controller.ApiMapping.ORDER_REST_URL;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ORDER_REST_URL)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private static final Logger log = getLogger(OrderController.class);

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('order:write')")
    public ResponseEntity<OrderTo> create(
            @RequestBody OrderTo orderTo,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("POST request: create()");
        OrderTo created = orderService.create(
                orderTo,
                orderTo.getClientId(),
                authenticated.getOrganizationId(),
                authenticated.getId()
        );
        URI resourceUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ORDER_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity
                .created(resourceUri)
                .body(created);
    }

    @GetMapping(value = "/clients/{id}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('order:read')")
    public ResponseEntity<List<OrderTo>> getAllByClientId(
            @PathVariable Integer id,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("GET request: getAllByClientId({})", id);
        return ResponseEntity.ok()
                .body(
                        orderService.getAllByClientId(
                                id,
                                authenticated.getId()
                        )
                );
    }
}
