package by.ttre16.briana.controller;

import by.ttre16.briana.annotation.Authenticated;
import by.ttre16.briana.security.AuthenticatedEmployee;
import by.ttre16.briana.service.CategoryService;
import by.ttre16.briana.transport.CategoryTo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static by.ttre16.briana.controller.ApiMapping.CATEGORY_REST_URL;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(CATEGORY_REST_URL)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private static final Logger log = getLogger(CategoryController.class);

    @PostMapping(
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('category:write')")
    public ResponseEntity<CategoryTo> create(
            @RequestBody CategoryTo categoryTo,
            @RequestParam("file") MultipartFile file,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("POST request: create()");
        CategoryTo created = categoryService.create(
                categoryTo,
                authenticated.getOrganizationId(),
                file,
                authenticated.getId()
        );
        URI resourceUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CATEGORY_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity
                .created(resourceUri)
                .body(categoryTo);
    }
}
