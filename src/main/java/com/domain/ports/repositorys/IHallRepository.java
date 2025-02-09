package com.domain.ports.repositorys;

import com.domain.models.DomainHall;
import com.domain.models.DomainSeat;
import java.util.List;

public interface IHallRepository extends BaseRepository<DomainHall, Long> {
    boolean isAllSeatsFullByHallId(Long id);
    List<DomainSeat> showEmptySeats(Long id);
}
