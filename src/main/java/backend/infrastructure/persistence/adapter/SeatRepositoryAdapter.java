package backend.infrastructure.persistence.adapter;

import backend.domain.ports.repositorys.ISeatRepository;
import backend.infrastructure.persistence.entities.Seat;
import backend.infrastructure.persistence.repositorys.ISeatJpaRepository;
import backend.domain.models.DomainSeat;
import backend.infrastructure.persistence.mapper.SeatMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SeatRepositoryAdapter implements ISeatRepository {

    private final ISeatJpaRepository seatJpaRepository;
    private final SeatMapper seatMapper;

    public SeatRepositoryAdapter(ISeatJpaRepository seatJpaRepository, SeatMapper seatMapper) {
        this.seatJpaRepository = seatJpaRepository;
        this.seatMapper = seatMapper;
    }
    @Override
    public List<DomainSeat> findAllById(List<Long> ids) {
        return seatJpaRepository.findAllById(ids)
                .stream()
                .map(seatMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<DomainSeat> getAllSeatsFromHall(Long hallId) {
        System.out.println("🪑 getAllSeatsFromHall called with hallId = " + hallId);
        List<Seat> seats = seatJpaRepository.findByHallId(hallId);
        System.out.println("🪑 Found " + seats.size() + " seat(s) in DB");

        return seats.stream()
                .map(seatMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public DomainSeat save(DomainSeat domainSeat) {
        Seat entity = seatMapper.toEntity(domainSeat);
        return seatMapper.toDomain(seatJpaRepository.save(entity));
    }

    @Override
    public void saveAll(List<DomainSeat> domainSeats) {
        List<Seat> entities = domainSeats.stream()
                .map(seatMapper::toEntity)
                .collect(Collectors.toList());
        seatJpaRepository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        seatJpaRepository.deleteById(id);
    }

    @Override
    public DomainSeat update(DomainSeat dto, Long aLong) {
        return null;
    }

    @Override
    public Optional<DomainSeat> findById(Long id) {
        return seatJpaRepository.findById(id).map(seatMapper::toDomain);
    }

    @Override
    public List<DomainSeat> findAll() {
        return seatJpaRepository.findAll().stream()
                .map(seatMapper::toDomain)
                .collect(Collectors.toList());
    }
}
