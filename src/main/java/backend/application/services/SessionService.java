package backend.application.services;

import backend.domain.ports.repositorys.ISessionRepository;
import backend.domain.models.DomainSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessionService extends BaseService<DomainSession, Long> {
    private final ISessionRepository sessionRepository;

    public SessionService(ISessionRepository sessionRepository) {
        super(sessionRepository);
        this.sessionRepository = sessionRepository;
    }

    public LocalDateTime endTimeBySessionId(Long sessionId) {
        return sessionRepository.endTimeBySessionId(sessionId);
    }

    @Override
    public Optional<DomainSession> findById(Long id) {
        return sessionRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        sessionRepository.deleteById(id);
    }
}
