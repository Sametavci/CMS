package com.infrastructure.persistence.mapper;

import com.domain.models.DomainSeat;
import com.infrastructure.persistence.entities.Seat;
import org.springframework.stereotype.Component;

@Component("persistenceSeatMapper")
public class SeatMapper implements BaseMapper<Seat, DomainSeat> {

    @Override
    public Seat toEntity(DomainSeat domain) {
        if (domain == null) return null;
        Seat seat = new Seat();
        seat.setId(domain.getId());
        seat.setSeatRow(domain.getSeatRow());
        seat.setSeatColumn(domain.getSeatColumn());
        seat.setBooked(domain.isBooked());
        seat.setSeatType(domain.getSeatType());
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
        return domain;
    }
}
