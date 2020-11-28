package by.ttre16.briana.service;

import by.ttre16.briana.entity.*;
import by.ttre16.briana.repository.CategoryRepository;
import by.ttre16.briana.transport.CategoryTo;
import by.ttre16.briana.transport.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static by.ttre16.briana.exception.EntityNotFoundException.with;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ImageService imageService;
    private final EventService eventService;
    private final EmployeeService employeeService;

    public CategoryTo create(
            CategoryTo categoryTo,
            Integer organizationId,
            MultipartFile file,
            Integer publisherId) {
        Category category = categoryMapper.toEntity(categoryTo);
        String uploadedImagePath = imageService.upload(
                file,
                "category:" + category.getName()
        );
        category.setImagePath(uploadedImagePath);
        Category saved = categoryRepository.save(category);
        Employee publisher = employeeService.get(publisherId, organizationId);
        eventService.push(
                new Event(
                        publisher,
                        EventType.ADD,
                        MessageTopic.CATEGORY,
                        saved.getId()
                )
        );
        return categoryMapper.toDto(category);
    }

    public void update(
            CategoryTo categoryTo,
            Integer organizationId,
            Integer publisherId) {
        Category old = get(categoryTo.getId(), organizationId);
        categoryMapper.copyProperties(old, categoryTo);
        Category updated = categoryRepository.update(old);
        eventService.push(
                new Event(
                        employeeService.get(publisherId, organizationId),
                        EventType.UPDATE,
                        MessageTopic.CATEGORY,
                        updated.getId()
                )
        );
    }

    @Transactional(readOnly = true)
    public Category get(Integer id, Integer organizationId) {
        return categoryRepository.get(id, organizationId)
                .orElseThrow(() -> with(
                        Category.class, id
                ));
    }
}
