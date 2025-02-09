package com.infrastructure.persistence.repositorys;

import com.infrastructure.persistence.entities.Session;
import org.springframework.stereotype.Repository;

@Repository
public interface ISessionJpaRepository extends BaseJpaRepository<Session, Long> {
}
