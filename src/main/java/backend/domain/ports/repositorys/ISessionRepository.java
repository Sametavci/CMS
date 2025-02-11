package backend.domain.ports.repositorys;

import backend.domain.models.DomainSession;
import java.time.LocalDateTime;

public interface ISessionRepository extends BaseRepository<DomainSession, Long> {
    LocalDateTime endTimeBySessionId(Long sessionId);
}
