package backend.application.services;

import backend.domain.ports.repositorys.ISeatRepository;
import backend.domain.models.DomainSeat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService extends BaseService<DomainSeat, Long> {
    private final ISeatRepository seatRepository;

    public SeatService(ISeatRepository seatRepository) {
        super(seatRepository);
        this.seatRepository = seatRepository;
    }
    public List<DomainSeat> getAllSeatsFromHall(Long hallId){ return seatRepository.getAllSeatsFromHall(hallId); }
}
