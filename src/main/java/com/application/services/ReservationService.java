package com.application.services;

import com.domain.models.DomainReservation;
import com.domain.ports.repositorys.IReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService extends BaseService<DomainReservation, Long> {
    private final IReservationRepository reservationRepository;

    public ReservationService(IReservationRepository reservationRepository) {
        super(reservationRepository);
        this.reservationRepository = reservationRepository;
    }

    public Double calculateDiscountedPrice(Long id, Double discountPercentage) {
        DomainReservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Double originalPrice = reservation.getSession().getPrice();

        if (originalPrice <= 0 || discountPercentage <= 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Invalid price or discount percentage");
        }

        return originalPrice - (originalPrice * (discountPercentage / 100));
    }

    public boolean checkCustomerDiscountEligibility(Long customerId) {
        return reservationRepository.checkCustomerDiscountEligibility(customerId);
    }

    @Override
    public Optional<DomainReservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
