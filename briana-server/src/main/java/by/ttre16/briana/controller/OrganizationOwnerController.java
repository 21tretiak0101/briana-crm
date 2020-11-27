package by.ttre16.briana.controller;

import by.ttre16.briana.service.OrganizationOwnerService;
import by.ttre16.briana.transport.EmployeeTo;
import by.ttre16.briana.transport.OrganizationOwner;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static by.ttre16.briana.controller.ApiMapping.OWNER_REST_URL;

@RestController
@RequestMapping(OWNER_REST_URL)
@RequiredArgsConstructor
public class OrganizationOwnerController {
    private final OrganizationOwnerService organizationOwnerService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeTo> create(
            @RequestBody OrganizationOwner organizationOwner) {
        EmployeeTo created = organizationOwnerService.create(organizationOwner);
        URI resourceUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity
                .created(resourceUri)
                .body(created);
    }
}
