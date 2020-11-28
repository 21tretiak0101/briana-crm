package by.ttre16.briana.service;

import by.ttre16.briana.entity.Product;
import by.ttre16.briana.repository.ProductRepository;
import by.ttre16.briana.transport.ProductTo;
import by.ttre16.briana.transport.mapper.ProductMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static by.ttre16.briana.data.ProductTestData.PRODUCT22_ID;
import static by.ttre16.briana.data.ProductTestData.PRODUCTS;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest extends AbstractServiceTest {
    private ProductService productService;

    @Mock
    private EventService eventService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private ImageService imageService;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    private final Product product = new Product();

    @Before
    public void setUp() {
        productService = new ProductService(
                eventService,
                employeeService,
                imageService,
                productRepository,
                productMapper
        );

        BeanUtils.copyProperties(PRODUCTS.get(PRODUCT22_ID), product);
        when(productRepository.get(anyInt(), anyInt()))
                .thenReturn(Optional.of(product));
        when(employeeService.get(anyInt(), anyInt())).thenReturn(publisher);
        when(productMapper.toEntity(any())).thenReturn(product);
    }

    @Test
    public void create() {
        when(productRepository.save(any())).thenReturn(product);
        productService.create(
                new ProductTo(),
                organizationId,
                new MockMultipartFile("test", new byte[]{}),
                publisher.getId()
        );
        verify(productRepository).save(any());
        verify(imageService).upload(
                any(),
                eq("product:" + product.getName())
        );
    }

    @Test
    public void updatePhoto() {
        productService.updatePhoto(
                product.getId(),
                organizationId,
                new MockMultipartFile("test", new byte[]{}),
                publisher.getId()
        );
        verify(imageService).delete(anyString());
        verify(imageService).upload(
                any(),
                eq("product:" + product.getName())
        );
    }

    @Test
    public void get() {
        productService.get(product.getId(), organizationId);
        verify(productRepository).get(product.getId(), organizationId);
    }

    @Test
    public void getDto() {
        productService.getDto(product.getId(), organizationId);
        verify(productMapper).toDto(product);
    }
}
