package by.ttre16.briana.controller;

import by.ttre16.briana.annotation.Authenticated;
import by.ttre16.briana.security.AuthenticatedEmployee;
import by.ttre16.briana.service.EmployeeService;
import by.ttre16.briana.transport.EmployeeTo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static by.ttre16.briana.controller.ApiMapping.EMPLOYEE_REST_URL;
import static by.ttre16.briana.controller.ApiValidation.assureThatIdConsistent;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(EMPLOYEE_REST_URL)
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('employee:write')")
public class EmployeeController {
    private final EmployeeService employeeService;
    private static final Logger log = getLogger(EmployeeController.class);

    @GetMapping(
            value = "/{id}",
            produces = APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('employee:read')")
    public ResponseEntity<EmployeeTo> get(
            @PathVariable Integer id,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("GET request: get({})", id);
        return ResponseEntity.ok()
                .body(
                        employeeService.getDto(
                                id,
                                authenticated.getOrganizationId()
                        )
                );
    }

    @GetMapping(
            value = "/{id}/image",
            produces = {IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE}
    )
    @PreAuthorize("hasAuthority('employee:read')")
    public ResponseEntity<byte[]> getPhoto(
            @PathVariable Integer id,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("GET request: getPhoto({})", id);
        return ResponseEntity.ok()
                .body(
                        employeeService.downloadPhoto(
                                id,
                                authenticated.getOrganizationId()
                        )
                );

    }

    @PostMapping(
            consumes = {APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE},
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeTo> create(
            @RequestBody EmployeeTo employeeTo,
            @RequestParam("file") MultipartFile file,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("POST request: create()");
        EmployeeTo created = employeeService.create(
                employeeTo,
                authenticated.getOrganizationId(),
                file,
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
            @RequestBody EmployeeTo employeeTo,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("PUT request: update({})", id);
        assureThatIdConsistent(employeeTo, id);
        employeeService.update(
                employeeTo,
                authenticated.getOrganizationId(),
                authenticated.getId()
        );
    }

    @PutMapping(value = "/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePhoto(
            @PathVariable Integer employeeId,
            @RequestParam("file") MultipartFile file,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("PUT request: updatePhoto({})", employeeId);
        employeeService.updatePhoto(
                employeeId,
                authenticated.getOrganizationId(),
                file,
                authenticated.getId()
        );
    }
}
