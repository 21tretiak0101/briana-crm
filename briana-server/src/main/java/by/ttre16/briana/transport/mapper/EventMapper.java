package by.ttre16.briana.transport.mapper;

import by.ttre16.briana.entity.Event;
import by.ttre16.briana.transport.EventTo;
import by.ttre16.briana.transport.mapper.post.OrganizationPostMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrganizationPostMapper.class)
public interface EventMapper {
    @Mapping(target = "publisherId", source = "publisher.id")
    @Mapping(target = "organizationId", source = "organization.id")
    EventTo toDto(Event entity);
}
