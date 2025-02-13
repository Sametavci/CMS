package backend.application.services;

import backend.domain.models.DomainHall;
import backend.domain.models.DomainSeat;
import backend.domain.ports.repositorys.IHallRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HallService extends BaseService<DomainHall, Long> {
    private final IHallRepository hallRepository;

    public HallService(IHallRepository hallRepository) {
        super(hallRepository);
        this.hallRepository = hallRepository;
    }

    public boolean isAllSeatsFullByHallId(Long id) {
        return hallRepository.isAllSeatsFullByHallId(id);
    }

    public List<DomainSeat> showEmptySeats(Long id) {

        return hallRepository.showEmptySeats(id);
    }
}
