package com.infrastructure.persistence.repositorys;

import com.infrastructure.persistence.entities.SubscriptedCustomer;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubscriptionCustomerJpaRepository extends BaseJpaRepository<SubscriptedCustomer, Long> {
}
