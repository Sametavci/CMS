package com.infrastructure.persistence.repositorys;

import com.domain.ports.repositorys.BaseRepository;
import com.infrastructure.persistence.entities.Hall;
import org.springframework.stereotype.Repository;

@Repository
public interface IHallJpaRepository extends BaseJpaRepository<Hall, Long> {
    Hall findByName(String title);
}
