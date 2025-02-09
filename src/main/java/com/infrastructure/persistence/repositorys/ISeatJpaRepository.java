package com.infrastructure.persistence.repositorys;

import com.infrastructure.persistence.entities.Seat;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeatJpaRepository extends BaseJpaRepository<Seat, Long> {
}
