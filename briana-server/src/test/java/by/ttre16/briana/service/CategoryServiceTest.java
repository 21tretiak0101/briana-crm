package by.ttre16.briana.service;

import by.ttre16.briana.entity.Category;
import by.ttre16.briana.repository.CategoryRepository;
import by.ttre16.briana.transport.CategoryTo;
import by.ttre16.briana.transport.mapper.CategoryMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static by.ttre16.briana.data.CategoryTestData.CATEGORIES;
import static by.ttre16.briana.data.CategoryTestData.CATEGORY18_ID;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest extends AbstractServiceTest {
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private ImageService imageService;

    @Mock
    private EventService eventService;

    @Mock
    private EmployeeService employeeService;

    private final Category category = new Category();

    @Before
    public void setUp() {
        categoryService = new CategoryService(
                categoryRepository,
                categoryMapper,
                imageService,
                eventService,
                employeeService
        );

        BeanUtils.copyProperties(CATEGORIES.get(CATEGORY18_ID), this.category);
        when(categoryRepository.get(anyInt(), anyInt()))
                .thenReturn(Optional.of(category));
        when(employeeService.get(anyInt(), anyInt())).thenReturn(publisher);
        when(categoryMapper.toEntity(any())).thenReturn(category);
    }

    @Test
    public void create() {
        when(categoryRepository.save(any())).thenReturn(category);
        categoryService.create(
                new CategoryTo(),
                organizationId,
                new MockMultipartFile("test", new byte[]{}),
                publisher.getId()
        );
        verify(categoryRepository).save(any());
        verify(imageService).upload(
                any(),
                eq("category:" + category.getName())
        );
    }

    @Test
    public void update() {
        CategoryTo categoryTo = new CategoryTo();
        categoryTo.setId(category.getId());
        when(categoryRepository.update(any())).thenReturn(category);
        categoryService.update(categoryTo, organizationId, publisher.getId());
        verify(categoryMapper).copyProperties(category, categoryTo);
        verify(categoryRepository).update(any());
    }

    @Test
    public void get() {
        categoryService.get(category.getId(), organizationId);
        verify(categoryRepository).get(category.getId(), organizationId);
    }
}
