package backend.infrastructure.persistence.mapper;

import backend.infrastructure.persistence.entities.Reservation;
import backend.domain.models.DomainReservation;
import backend.infrastructure.persistence.repositorys.ICustomerJpaRepository;
import backend.infrastructure.persistence.repositorys.IMovieJpaRepository;
import backend.infrastructure.persistence.repositorys.ISeatJpaRepository;
import backend.infrastructure.persistence.repositorys.ISessionJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("persistenceReservationMapper")
public class ReservationMapper implements BaseMapper<Reservation, DomainReservation> {

    private final ISessionJpaRepository iSessionJpaRepository;
    private final ICustomerJpaRepository iCustomerJpaRepository;
    private final ISeatJpaRepository iSeatJpaRepository;

    public ReservationMapper(ISessionJpaRepository iSessionJpaRepository, ICustomerJpaRepository iCustomerJpaRepository, ISeatJpaRepository iSeatJpaRepository) {
        this.iSessionJpaRepository = iSessionJpaRepository;
        this.iCustomerJpaRepository = iCustomerJpaRepository;
        this.iSeatJpaRepository = iSeatJpaRepository;
    }

    @Override
    public Reservation toEntity(DomainReservation domain) {
        if (domain == null) return null;
        Reservation reservation = new Reservation();
        reservation.setId(domain.getId());
        reservation.setCustomer(iCustomerJpaRepository.findById(domain.getCustomer()).orElseThrow());
        reservation.setSession(iSessionJpaRepository.findById(domain.getSession()).orElseThrow());
        reservation.setSeat(iSeatJpaRepository.findById(domain.getSeat()).orElseThrow());
        return reservation;
    }

    @Override
    public DomainReservation toDomain(Reservation entity) {
        if (entity == null) return null;
        DomainReservation domain = new DomainReservation();
        domain.setId(entity.getId());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        domain.setSession(entity.getSession().getId());
        domain.setCustomer(entity.getCustomer().getId());
        domain.setSeat(entity.getSeat().getId());
        return domain;
    }
    @Override
    public void update(Reservation entity, DomainReservation dto) {
        entity.setCustomer(iCustomerJpaRepository.findById(dto.getCustomer()).orElseThrow());
        entity.setSeat(iSeatJpaRepository.findById(dto.getSeat()).orElseThrow());
        entity.setSession(iSessionJpaRepository.findById(dto.getSession()).orElseThrow());
        entity.setUpdatedAt(LocalDateTime.now());

    }
}
