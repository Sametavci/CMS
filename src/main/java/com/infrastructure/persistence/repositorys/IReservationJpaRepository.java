package com.infrastructure.persistence.repositorys;

import com.infrastructure.persistence.entities.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationJpaRepository extends BaseJpaRepository<Reservation, Long> {
}
