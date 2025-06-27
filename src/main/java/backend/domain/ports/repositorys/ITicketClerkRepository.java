package backend.domain.ports.repositorys;

import backend.domain.models.DomainTicketClerk;
import backend.infrastructure.persistence.entities.TicketClerk;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITicketClerkRepository extends BaseRepository<TicketClerk, Long>{
    Optional<TicketClerk> findByUsername(String username);
}
