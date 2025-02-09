package com.infrastructure.persistence.repositorys;

import com.infrastructure.persistence.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerJpaRepository extends BaseJpaRepository<Customer, Long> {
}
