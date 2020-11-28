package by.ttre16.briana.controller;

import by.ttre16.briana.annotation.Authenticated;
import by.ttre16.briana.security.AuthenticatedEmployee;
import by.ttre16.briana.service.ProductService;
import by.ttre16.briana.transport.ProductTo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static by.ttre16.briana.controller.ApiMapping.PRODUCT_REST_URL;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(PRODUCT_REST_URL)
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('product:write')")
public class ProductController {
    private final ProductService productService;
    private static final Logger log = getLogger(ProductController.class);

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductTo> create(
            @RequestBody ProductTo productTo,
            @RequestParam("file") MultipartFile file,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("POST request: create()");
        ProductTo created = productService.create(
                productTo,
                authenticated.getOrganizationId(),
                file,
                authenticated.getId()
        );
        URI resourceUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PRODUCT_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity
                .created(resourceUri)
                .body(created);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePhoto(
            @PathVariable("id") Integer id,
            @RequestParam("file") MultipartFile file,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("PUT request: updatePhoto({})", id);
        productService.updatePhoto(
                id,
                authenticated.getOrganizationId(),
                file,
                authenticated.getId()
        );
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('product:read')")
    public ResponseEntity<ProductTo> get(
            @PathVariable Integer id,
            @Authenticated AuthenticatedEmployee authenticated) {
        return ResponseEntity.ok()
                .body(
                        productService.getDto(
                                id,
                                authenticated.getOrganizationId()
                        )
                );
    }
}
