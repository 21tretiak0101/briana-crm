package by.ttre16.briana.service;

import by.ttre16.briana.entity.Employee;
import by.ttre16.briana.entity.Event;
import by.ttre16.briana.entity.EventType;
import by.ttre16.briana.entity.MessageTopic;
import by.ttre16.briana.exception.EntityNotFoundException;
import by.ttre16.briana.repository.EmployeeRepository;
import by.ttre16.briana.transport.EmployeeTo;
import by.ttre16.briana.transport.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final ImageService imageService;
    private final EmployeeRepository employeeRepository;
    private final EventService eventService;
    private final EmployeeMapper employeeMapper;

    public byte[] downloadPhoto(Integer employeeId, Integer organizationId) {
        Employee employee = get(employeeId, organizationId);
        return imageService.download(employee.getPhotoPath());
    }

    public EmployeeTo create(
            EmployeeTo employeeTo,
            Integer organizationId,
            MultipartFile file,
            Integer publisherId) {
        Employee employee = employeeMapper.toEntity(employeeTo);
        String uploadedPhotoPath = imageService.upload(
                file,
                "employee:" + employee.getEmail()
        );
        employee.setPhotoPath(uploadedPhotoPath);
        Employee saved = employeeRepository.save(employee);
        Employee publisher = get(publisherId, organizationId);
        eventService.push(
                new Event(
                        publisher,
                        EventType.ADD,
                        MessageTopic.EMPLOYEE,
                        saved.getId()
                )
        );
        return employeeMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public Employee get(Integer id, Integer organizationId) {
        return employeeRepository.get(id, organizationId)
                .orElseThrow(() -> EntityNotFoundException.with(
                        Employee.class, id
                ));
    }

    public EmployeeTo getDto(Integer id, Integer organizationId) {
        return employeeMapper.toDto(get(id, organizationId));
    }

    public void update(
            EmployeeTo employeeTo,
            Integer organizationId,
            Integer publisherId) {
        Employee old = get(employeeTo.getId(), organizationId);
        employeeMapper.copyProperties(old, employeeTo);
        Employee updated = employeeRepository.update(old);
        eventService.push(
                new Event(
                        get(publisherId, organizationId),
                        EventType.UPDATE,
                        MessageTopic.PHOTO,
                        updated.getId()
                )
        );
    }

    public void updatePhoto(
            Integer employeeId,
            Integer organizationId,
            MultipartFile file,
            Integer publisherId) {
        Employee employee = get(employeeId, organizationId);
        imageService.delete(employee.getPhotoPath());
        imageService.upload(file, "employee:" + employee.getEmail());
        eventService.push(
                new Event(
                        get(publisherId, organizationId),
                        EventType.UPDATE,
                        MessageTopic.PHOTO,
                        employee.getId()
                )
        );
    }
}
