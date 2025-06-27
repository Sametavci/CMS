package backend.infrastructure.persistence.repositorys;

import backend.infrastructure.persistence.entities.TicketClerk;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITickerClerkJpaRepository extends BaseJpaRepository<TicketClerk, Long>{
    @Query("select u from TicketClerk u where u.email = :email")
    Optional<TicketClerk> findByEmail(String email);

}
