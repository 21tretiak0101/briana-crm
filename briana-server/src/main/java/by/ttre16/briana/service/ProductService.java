package by.ttre16.briana.service;

import by.ttre16.briana.entity.Event;
import by.ttre16.briana.entity.EventType;
import by.ttre16.briana.entity.MessageTopic;
import by.ttre16.briana.entity.Product;
import by.ttre16.briana.exception.EntityNotFoundException;
import by.ttre16.briana.repository.ProductRepository;
import by.ttre16.briana.transport.ProductTo;
import by.ttre16.briana.transport.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final EventService eventService;
    private final EmployeeService employeeService;
    private final ImageService imageService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductTo create(
            ProductTo productTo,
            Integer organizationId,
            MultipartFile file,
            Integer publisherId) {
        Product product = productMapper.toEntity(productTo);
        String uploadedImagePath = imageService.upload(
                file,
                "product:" + product.getName()
        );
        product.setImagePath(uploadedImagePath);
        Product savedProduct = productRepository.save(product);
        eventService.push(
                new Event(
                        employeeService.get(publisherId, organizationId),
                        EventType.ADD,
                        MessageTopic.PRODUCT,
                        savedProduct.getId()
                )
        );
        return productMapper.toDto(savedProduct);
    }

    public void updatePhoto(
            Integer productId,
            Integer organizationId,
            MultipartFile file,
            Integer publisherId) {
        Product product = get(productId, organizationId);
        imageService.delete(product.getImagePath());
        imageService.upload(file, "product:" + product.getName());
        eventService.push(
                new Event(
                        employeeService.get(publisherId, organizationId),
                        EventType.UPDATE,
                        MessageTopic.PRODUCT,
                        product.getId()
                )
        );
    }

    @Transactional(readOnly = true)
    public Product get(Integer id, Integer organizationId) {
        return productRepository.get(id, organizationId)
                .orElseThrow(() -> EntityNotFoundException.with(
                        Product.class, id
                ));
    }

    @Transactional(readOnly = true)
    public ProductTo getDto(Integer id, Integer organizationId) {
        return productMapper.toDto(get(id, organizationId));
    }
}
