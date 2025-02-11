package backend.domain.ports.repositorys;

import backend.domain.models.DomainSeat;
import backend.domain.models.DomainHall;

import java.util.List;

public interface IHallRepository extends BaseRepository<DomainHall, Long> {
    boolean isAllSeatsFullByHallId(Long id);
    List<DomainSeat> showEmptySeats(Long id);
}
