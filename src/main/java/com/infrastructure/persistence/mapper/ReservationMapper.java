package com.infrastructure.persistence.mapper;

import com.domain.models.DomainReservation;
import com.infrastructure.persistence.entities.Reservation;
import org.springframework.stereotype.Component;

@Component("persistenceReservationMapper")
public class ReservationMapper implements BaseMapper<Reservation, DomainReservation> {

    @Override
    public Reservation toEntity(DomainReservation domain) {
        if (domain == null) return null;
        Reservation reservation = new Reservation();
        reservation.setId(domain.getId());
        return reservation;
    }

    @Override
    public DomainReservation toDomain(Reservation entity) {
        if (entity == null) return null;
        DomainReservation domain = new DomainReservation();
        domain.setId(entity.getId());
        return domain;
    }
}
