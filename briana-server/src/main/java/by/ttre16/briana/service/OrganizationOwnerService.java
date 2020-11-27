package by.ttre16.briana.service;

import by.ttre16.briana.entity.Employee;
import by.ttre16.briana.entity.EmployeePermission;
import by.ttre16.briana.entity.Organization;
import by.ttre16.briana.entity.Position;
import by.ttre16.briana.repository.EmployeeRepository;
import by.ttre16.briana.repository.OrganizationRepository;
import by.ttre16.briana.repository.PositionRepository;
import by.ttre16.briana.transport.EmployeeTo;
import by.ttre16.briana.transport.OrganizationOwner;
import by.ttre16.briana.transport.mapper.EmployeeMapper;
import by.ttre16.briana.transport.mapper.OrganizationOwnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrganizationOwnerService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationOwnerMapper organizationOwnerMapper;
    private final EmployeeMapper employeeMapper;

    @Transactional
    public EmployeeTo create(OrganizationOwner organizationOwner) {
        Employee owner = organizationOwnerMapper.getOwner(organizationOwner);

        Organization ownerOrganization = organizationRepository.save(
                organizationOwnerMapper.getOrganization(organizationOwner)
        );

        Position position = new Position();
        position.setName(organizationOwner.getClass().getSimpleName());
        position.setPermissions(EmployeePermission.getAll());
        position.setOrganization(ownerOrganization);
        Position ownerPosition = positionRepository.save(position);

        owner.setPosition(ownerPosition);
        owner.setOrganization(ownerOrganization);
        Employee saved = employeeRepository.save(owner);
        return employeeMapper.toDto(saved);
    }
}
