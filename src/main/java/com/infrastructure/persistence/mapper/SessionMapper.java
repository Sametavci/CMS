package com.infrastructure.persistence.mapper;

import com.domain.models.DomainSession;
import com.infrastructure.persistence.entities.Session;
import org.springframework.stereotype.Component;

@Component("persistenceSessionMapper")
public class SessionMapper implements BaseMapper<Session, DomainSession> {

    @Override
    public Session toEntity(DomainSession domain) {
        if (domain == null) return null;
        Session session = new Session();
        session.setId(domain.getId());
        session.setStartTime(domain.getStartTime());
        session.setPrice(domain.getPrice());
        return session;
    }

    @Override
    public DomainSession toDomain(Session entity) {
        if (entity == null) return null;
        DomainSession domain = new DomainSession();
        domain.setId(entity.getId());
        domain.setStartTime(entity.getStartTime());
        domain.setPrice(entity.getPrice());
        return domain;
    }
}
