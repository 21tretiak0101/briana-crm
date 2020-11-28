package by.ttre16.briana.service;

import by.ttre16.briana.entity.Organization;
import by.ttre16.briana.exception.EntityNotFoundException;
import by.ttre16.briana.repository.OrganizationRepository;
import by.ttre16.briana.transport.OrganizationTo;
import by.ttre16.briana.transport.mapper.OrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public Organization get(Integer id) {
        return organizationRepository.get(id)
                .orElseThrow(() -> EntityNotFoundException.with(
                        Organization.class, id
                ));
    }

    public OrganizationTo getDto(Integer id) {
        return organizationMapper.toDto(get(id));
    }
}
