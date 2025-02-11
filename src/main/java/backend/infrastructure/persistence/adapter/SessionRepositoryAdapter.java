package backend.infrastructure.persistence.adapter;

import backend.domain.models.DomainSeat;
import backend.domain.ports.repositorys.ISessionRepository;
import backend.infrastructure.persistence.entities.Seat;
import backend.infrastructure.persistence.entities.Session;
import backend.infrastructure.persistence.repositorys.ISessionJpaRepository;
import backend.domain.models.DomainSession;
import backend.infrastructure.persistence.mapper.SessionMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
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
        Session session = sessionJpaRepository.findById(sessionId).orElseThrow();
        return session.getStartTime().plusMinutes(session.getMovie().getDuration());
    }
    public DomainSession update(DomainSession dto, Long id){
        DomainSession databaseElement = findById(id).orElseThrow(
                () -> new RuntimeException("Entity with that id couldnt find" + id)
        );
        Session entity = mapper.toEntity(databaseElement);
        mapper.update(entity, dto);
        Session savedEntity = sessionJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);

    }
}
