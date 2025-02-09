package com.domain.ports.repositorys;

import com.domain.models.DomainSession;
import java.time.LocalDateTime;

public interface ISessionRepository extends BaseRepository<DomainSession, Long> {
    LocalDateTime endTimeBySessionId(Long sessionId);
}
