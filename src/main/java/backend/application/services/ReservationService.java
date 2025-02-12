package backend.application.services;

import backend.domain.models.DomainReservation;
import backend.domain.ports.repositorys.IReservationRepository;
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
        return reservationRepository.calculateDiscountedPrice(id, discountPercentage);
    }

    public boolean checkCustomerDiscountEligibility(Long customerId) {
        return reservationRepository.checkCustomerDiscountEligibility(customerId);
    }

}
