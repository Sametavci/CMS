package backend.infrastructure.persistence.repositorys;

import backend.infrastructure.persistence.entities.Seat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISeatJpaRepository extends BaseJpaRepository<Seat, Long> {
    List<Seat> findByHallId(Long hallId);  // gerekli metot

}
