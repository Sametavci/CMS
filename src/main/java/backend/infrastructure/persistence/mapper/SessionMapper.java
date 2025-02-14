package backend.infrastructure.persistence.mapper;

import backend.domain.models.DomainSeat;
import backend.infrastructure.persistence.entities.Seat;
import backend.infrastructure.persistence.entities.Session;
import backend.domain.models.DomainSession;
import backend.infrastructure.persistence.repositorys.IHallJpaRepository;
import backend.infrastructure.persistence.repositorys.IMovieJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("persistenceSessionMapper")
public class SessionMapper implements BaseMapper<Session, DomainSession> {

    private final IMovieJpaRepository iMovieJpaRepository;
    private final IHallJpaRepository iHallJpaRepository;

    public SessionMapper(IMovieJpaRepository iMovieJpaRepository, IHallJpaRepository iHallJpaRepository) {
        this.iMovieJpaRepository = iMovieJpaRepository;
        this.iHallJpaRepository = iHallJpaRepository;
    }

    @Override
    public Session toEntity(DomainSession domain) {

        if (domain == null) return null;
        Session session = new Session();
        session.setId(domain.getId());
        session.setStartTime(domain.getStartTime());
        session.setPrice(domain.getPrice());
        session.setMovie(iMovieJpaRepository.findById(domain.getMovie()).orElseThrow());
        session.setHall(iHallJpaRepository.findById(domain.getHall()).orElseThrow());
        return session;
    }

    @Override
    public DomainSession toDomain(Session entity) {
        if (entity == null) return null;
        DomainSession domain = new DomainSession();
        domain.setId(entity.getId());
        domain.setStartTime(entity.getStartTime());
        domain.setPrice(entity.getPrice());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setMovie(entity.getMovie().getId());
        domain.setHall(entity.getHall().getId());
        return domain;
    }

    @Override
    public Session update(Session entity, DomainSession dto) {
        entity.setId(dto.getId());
        entity.setStartTime(LocalDateTime.now()); // String -> LocalDateTime
        entity.setPrice(dto.getPrice());
        entity.setMovie(iMovieJpaRepository.findById(dto.getMovie()).orElseThrow());
        entity.setHall(iHallJpaRepository.findById(dto.getHall()).orElseThrow());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
}
