package by.ttre16.briana.controller;

import by.ttre16.briana.annotation.Authenticated;
import by.ttre16.briana.dto.EmployeeTo;
import by.ttre16.briana.dto.mapper.EmployeeMapper;
import by.ttre16.briana.entity.Employee;
import by.ttre16.briana.security.AuthenticatedEmployee;
import by.ttre16.briana.service.EmployeeService;
import by.ttre16.briana.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final ImageService imageService;
    private static final Logger log = getLogger(EmployeeController.class);

    @GetMapping(
            value = "/{id}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeTo> get(
            @PathVariable Integer id,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("GET request: get({})", id);
        Employee employee = employeeService.get(
                id,
                authenticated.getOrganizationId()
        );
        EmployeeTo employeeTo = employeeMapper.toDto(employee);
        employeeTo.setImage(
                imageService.download(employee.getPhotoPath())
        );
        return ResponseEntity.ok()
                .body(employeeTo);
    }

    @GetMapping(
            value = "/{id}/image",
            consumes = APPLICATION_JSON_VALUE,
            produces = {IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE}
    )
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
        Employee created = employeeService.create(
                employeeMapper.toEntity(employeeTo),
                authenticated.getOrganizationId(),
                file,
                authenticated.getId()
        );
        URI resourceUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(EMPLOYEE_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity
                .created(resourceUri)
                .body(employeeMapper.toDto(created));
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable Integer id,
            @RequestBody EmployeeTo employeeTo,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("PUT request: update({})", id);
        Employee employee = employeeMapper.toEntity(employeeTo);
        assureThatIdConsistent(employee, id);

        employeeService.update(
                employee,
                authenticated.getOrganizationId(),
                authenticated.getId()
        );
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePhoto(
            @PathVariable("id") Integer id,
            @RequestParam("file") MultipartFile file,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("PUT request: updatePhoto({})", id);
        employeeService.updatePhoto(
                id,
                authenticated.getOrganizationId(),
                file,
                authenticated.getId()
        );
    }
}
