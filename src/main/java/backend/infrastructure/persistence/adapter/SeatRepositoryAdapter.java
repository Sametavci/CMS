package backend.infrastructure.persistence.adapter;

import backend.domain.models.DomainReservation;
import backend.domain.ports.repositorys.ISeatRepository;
import backend.infrastructure.persistence.entities.Reservation;
import backend.infrastructure.persistence.entities.Seat;
import backend.infrastructure.persistence.repositorys.ISeatJpaRepository;
import backend.domain.models.DomainSeat;
import backend.infrastructure.persistence.mapper.SeatMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SeatRepositoryAdapter implements ISeatRepository {

    private final ISeatJpaRepository seatJpaRepository;
    private final SeatMapper mapper;

    @Autowired
    public SeatRepositoryAdapter(ISeatJpaRepository seatJpaRepository, SeatMapper mapper) {
        this.seatJpaRepository = seatJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DomainSeat save(DomainSeat entity) {
        Seat seatEntity = mapper.toEntity(entity);

        Seat savedEntity = seatJpaRepository.save(seatEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<DomainSeat> findById(Long id) {
        return seatJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DomainSeat> findAll() {
        return seatJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        seatJpaRepository.deleteById(id);
    }

    public DomainSeat update(DomainSeat dto, Long id){
        DomainSeat databaseElement = findById(id).orElseThrow(
                () -> new RuntimeException("Entity with that id couldnt find" + id)
        );
        Seat entity = mapper.toEntity(databaseElement);
        Seat savedEntity = seatJpaRepository.save(mapper.update(entity, dto));
        return mapper.toDomain(savedEntity);

    }

    public List<DomainSeat> getAllSeatsFromHall(Long hallId){
        return findAll().stream().filter(m -> m.getHall() == hallId).toList();
    }
}
