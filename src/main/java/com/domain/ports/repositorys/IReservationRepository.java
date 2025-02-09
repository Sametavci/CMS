package com.domain.ports.repositorys;

import com.domain.models.DomainReservation;

public interface IReservationRepository extends BaseRepository<DomainReservation, Long> {
    Double calculateDiscountedPrice(Long id, Double discountPercentage);
    boolean checkCustomerDiscountEligibility(Long customerId);
}
