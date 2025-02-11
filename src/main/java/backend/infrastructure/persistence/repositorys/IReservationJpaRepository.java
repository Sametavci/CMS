package backend.infrastructure.persistence.repositorys;

import backend.infrastructure.persistence.entities.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationJpaRepository extends BaseJpaRepository<Reservation, Long> {
}
