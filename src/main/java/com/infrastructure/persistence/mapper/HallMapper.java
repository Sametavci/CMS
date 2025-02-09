package com.infrastructure.persistence.mapper;

import com.domain.models.DomainHall;
import com.infrastructure.persistence.entities.Hall;
import org.springframework.stereotype.Component;

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
        return domain;
    }
}
