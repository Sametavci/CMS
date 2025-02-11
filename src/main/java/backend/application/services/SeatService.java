package backend.application.services;

import backend.domain.ports.repositorys.ISeatRepository;
import backend.domain.models.DomainSeat;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatService extends BaseService<DomainSeat, Long> {
    private final ISeatRepository seatRepository;

    public SeatService(ISeatRepository seatRepository) {
        super(seatRepository);
        this.seatRepository = seatRepository;
    }

    @Override
    public Optional<DomainSeat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        seatRepository.deleteById(id);
    }
}
