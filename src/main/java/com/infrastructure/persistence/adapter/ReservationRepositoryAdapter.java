package com.infrastructure.persistence.adapter;

import com.domain.models.DomainReservation;
import com.domain.ports.repositorys.IReservationRepository;
import com.infrastructure.persistence.entities.Reservation;
import com.infrastructure.persistence.mapper.ReservationMapper;
import com.infrastructure.persistence.repositorys.IReservationJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationRepositoryAdapter implements IReservationRepository {

    private final IReservationJpaRepository reservationJpaRepository;
    private final ReservationMapper mapper;

    @Autowired
    public ReservationRepositoryAdapter(IReservationJpaRepository reservationJpaRepository, ReservationMapper mapper) {
        this.reservationJpaRepository = reservationJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DomainReservation save(DomainReservation entity) {
        Reservation reservationEntity = mapper.toEntity(entity);
        Reservation savedEntity = reservationJpaRepository.save(reservationEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<DomainReservation> findById(Long id) {
        return reservationJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DomainReservation> findAll() {
        return reservationJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        reservationJpaRepository.deleteById(id);
    }

    @Override
    public Double calculateDiscountedPrice(Long id, Double discountPercentage) {
        return null;
    }

    @Override
    public boolean checkCustomerDiscountEligibility(Long customerId) {
        return false;
    }
}
