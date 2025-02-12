package backend.domain.ports.repositorys;

import backend.domain.models.DomainSeat;

import java.util.List;

public interface ISeatRepository extends BaseRepository<DomainSeat, Long> {
    public List<DomainSeat> getAllSeatsFromHall(Long hallId);
}
