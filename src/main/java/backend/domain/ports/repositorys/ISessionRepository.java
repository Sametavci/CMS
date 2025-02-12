package backend.domain.ports.repositorys;

import backend.domain.models.DomainSession;
import java.time.LocalDateTime;
import java.util.List;

public interface ISessionRepository extends BaseRepository<DomainSession, Long> {
    LocalDateTime endTimeBySessionId(Long sessionId);
    public List<DomainSession> getAllSessionsFromHall(Long hallId);
    public List<DomainSession> getAllSessionsFromMovie(Long movieId);
}
