package backend.infrastructure.persistence.repositorys;

import backend.infrastructure.persistence.entities.Session;
import org.springframework.stereotype.Repository;

@Repository
public interface ISessionJpaRepository extends BaseJpaRepository<Session, Long> {
}
