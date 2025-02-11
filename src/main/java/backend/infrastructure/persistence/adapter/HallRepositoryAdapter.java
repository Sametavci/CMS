package backend.infrastructure.persistence.adapter;

import backend.domain.models.DomainCustomer;
import backend.infrastructure.persistence.entities.Customer;
import backend.infrastructure.persistence.entities.Hall;
import backend.infrastructure.persistence.entities.Seat;
import backend.infrastructure.persistence.mapper.SeatMapper;
import backend.infrastructure.persistence.repositorys.IHallJpaRepository;
import backend.domain.models.DomainHall;
import backend.domain.models.DomainSeat;
import backend.domain.ports.repositorys.IHallRepository;
import backend.infrastructure.persistence.mapper.HallMapper;
import backend.infrastructure.persistence.repositorys.ISeatJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class HallRepositoryAdapter implements IHallRepository {

    private final IHallJpaRepository hallJpaRepository;
    private final HallMapper mapper;


    @Autowired
    public HallRepositoryAdapter(IHallJpaRepository hallJpaRepository, HallMapper mapper) {
        this.hallJpaRepository = hallJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DomainHall save(DomainHall entity) {
        Hall hallEntity = mapper.toEntity(entity);
        Hall savedEntity = hallJpaRepository.save(hallEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<DomainHall> findById(Long id) {
        return hallJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DomainHall> findAll() {
        return hallJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        hallJpaRepository.deleteById(id);
    }

    @Override
    public boolean isAllSeatsFullByHallId(Long id) {
        List<Seat> seats = hallJpaRepository.findById(id).get().getSeats();
        for(Seat seat : seats){
            if(!seat.isBooked()){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<DomainSeat> showEmptySeats(Long id) {
        final SeatMapper seatMapper = new SeatMapper(hallJpaRepository);
        return hallJpaRepository.findById(id).get().getSeats()
                .stream()
                .map(seatMapper::toDomain)
                .filter(m -> !m.isBooked())
                .toList();
    }
    public DomainHall update(DomainHall dto, Long id){
        DomainHall databaseElement = findById(id).orElseThrow(
                () -> new RuntimeException("Entity with that id couldnt find" + id)
        );
        Hall entity = mapper.toEntity(databaseElement);
        mapper.update(entity, dto);
        Hall savedEntity = hallJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);

    }
}
