package com.infrastructure.persistence.adapter;

import com.domain.models.DomainSession;
import com.domain.ports.repositorys.ISessionRepository;
import com.infrastructure.persistence.entities.Session;
import com.infrastructure.persistence.mapper.SessionMapper;
import com.infrastructure.persistence.repositorys.ISessionJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionRepositoryAdapter implements ISessionRepository {

    private final ISessionJpaRepository sessionJpaRepository;
    private final SessionMapper mapper;

    @Autowired
    public SessionRepositoryAdapter(ISessionJpaRepository sessionJpaRepository, SessionMapper mapper) {
        this.sessionJpaRepository = sessionJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DomainSession save(DomainSession entity) {
        Session sessionEntity = mapper.toEntity(entity);
        Session savedEntity = sessionJpaRepository.save(sessionEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<DomainSession> findById(Long id) {
        return sessionJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DomainSession> findAll() {
        return sessionJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        sessionJpaRepository.deleteById(id);
    }

    @Override
    public LocalDateTime endTimeBySessionId(Long sessionId) {
        return null;
    }
}
