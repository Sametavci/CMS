package com.application.services;

import com.domain.models.DomainSession;
import com.domain.ports.repositorys.ISessionRepository;
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
        DomainSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found with ID " + sessionId));
        int movieDuration = session.getMovie().getDuration();
        return session.getStartTime().plusMinutes(movieDuration);
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
