package by.ttre16.briana.controller;

import by.ttre16.briana.annotation.Authenticated;
import by.ttre16.briana.entity.Product;
import by.ttre16.briana.security.AuthenticatedEmployee;
import by.ttre16.briana.service.ProductService;
import by.ttre16.briana.transport.ProductTo;
import by.ttre16.briana.transport.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static by.ttre16.briana.controller.ApiMapping.PRODUCT_REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(PRODUCT_REST_URL)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private static final Logger log = getLogger(ProductController.class);

    public ResponseEntity<ProductTo> create(
            @RequestBody ProductTo productTo,
            @RequestParam("file") MultipartFile file,
            @Authenticated AuthenticatedEmployee authenticated) {
        log.info("POST request: create()");
        Product created = productService.create(
                productMapper.toEntity(productTo),
                authenticated.getOrganizationId(),
                file,
                authenticated.getId()
        );
        URI resourceUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(PRODUCT_REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity
                .created(resourceUri)
                .body(productMapper.toDto(created));
    }

    public void update() {

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
}
