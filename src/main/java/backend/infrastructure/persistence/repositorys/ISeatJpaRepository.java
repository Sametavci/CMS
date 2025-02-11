package backend.infrastructure.persistence.repositorys;

import backend.infrastructure.persistence.entities.Seat;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeatJpaRepository extends BaseJpaRepository<Seat, Long> {
}
