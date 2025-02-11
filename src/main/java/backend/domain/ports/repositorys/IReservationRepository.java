package backend.domain.ports.repositorys;

import backend.domain.models.DomainReservation;

public interface IReservationRepository extends BaseRepository<DomainReservation, Long> {
    Double calculateDiscountedPrice(Long id, Double discountPercentage);
    boolean checkCustomerDiscountEligibility(Long customerId);
}
