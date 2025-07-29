package backend.domain.ports.repositorys;

import backend.domain.models.DomainSeat;

import java.util.List;

public interface ISeatRepository extends BaseRepository<DomainSeat, Long> {
        List<DomainSeat> getAllSeatsFromHall(Long hallId);
    void saveAll(List<DomainSeat> seats);
    List<DomainSeat> findAllById(List<Long> ids);

}
