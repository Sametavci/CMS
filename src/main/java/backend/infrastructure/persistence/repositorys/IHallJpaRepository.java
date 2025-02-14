package backend.infrastructure.persistence.repositorys;

import backend.infrastructure.persistence.entities.Hall;
import org.springframework.stereotype.Repository;

@Repository
public interface IHallJpaRepository extends BaseJpaRepository<Hall, Long> {
}
