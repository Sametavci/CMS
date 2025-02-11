package backend.infrastructure.persistence.mapper;

import backend.infrastructure.persistence.entities.Hall;
import backend.domain.models.DomainHall;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("persistenceHallMapper")
public class HallMapper implements BaseMapper<Hall, DomainHall> {

    @Override
    public Hall toEntity(DomainHall domain) {
        if (domain == null) return null;
        Hall hall = new Hall();
        hall.setId(domain.getId());
        hall.setName(domain.getName());
        hall.setCapacity(domain.getCapacity());
        hall.setType(domain.getType());
        return hall;
    }

    @Override
    public DomainHall toDomain(Hall entity) {
        if (entity == null) return null;
        DomainHall domain = new DomainHall();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setCapacity(entity.getCapacity());
        domain.setType(entity.getType());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }
    @Override
    public void update(Hall hallEntity, DomainHall dto) {
        hallEntity.setName(dto.getName());
        hallEntity.setCapacity(dto.getCapacity());
        hallEntity.setType(dto.getType());
        hallEntity.setUpdatedAt(LocalDateTime.now());
    }
}
