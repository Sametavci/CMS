package backend.infrastructure.persistence.mapper;

import backend.infrastructure.persistence.entities.Seat;
import backend.domain.models.DomainSeat;
import backend.infrastructure.persistence.repositorys.IHallJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("persistenceSeatMapper")
public class SeatMapper implements BaseMapper<Seat, DomainSeat> {

    private final IHallJpaRepository iHallJpaRepository;

    public SeatMapper(IHallJpaRepository iHallJpaRepository) {
        this.iHallJpaRepository = iHallJpaRepository;
    }

    @Override
    public Seat toEntity(DomainSeat domain) {

        if (domain == null) return null;
        Seat seat = new Seat();
        seat.setId(domain.getId());
        seat.setSeatRow(domain.getSeatRow());
        seat.setSeatColumn(domain.getSeatColumn());
        seat.setBooked(domain.isBooked());
        seat.setSeatType(domain.getSeatType());
        seat.setHall(iHallJpaRepository.findById(domain.getHall()).orElseThrow());
        return seat;
    }

    @Override
    public DomainSeat toDomain(Seat entity) {
        if (entity == null) return null;
        DomainSeat domain = new DomainSeat();
        domain.setId(entity.getId());
        domain.setSeatRow(entity.getSeatRow());
        domain.setSeatColumn(entity.getSeatColumn());
        domain.setBooked(entity.isBooked());
        domain.setSeatType(entity.getSeatType());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setHall(entity.getHall().getId());
        return domain;
    }
    @Override
    public Seat update(Seat entity, DomainSeat dto) {
        entity.setSeatRow(entity.getSeatRow());
        entity.setSeatColumn(entity.getSeatColumn());
        entity.setBooked(entity.isBooked());
        entity.setHall(iHallJpaRepository.findById(dto.getHall()).orElseThrow());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setSeatType(dto.getSeatType());
        return entity;
    }
}
