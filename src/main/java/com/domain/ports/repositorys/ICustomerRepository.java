package com.domain.ports.repositorys;

import com.domain.models.DomainCustomer;

public interface ICustomerRepository extends BaseRepository<DomainCustomer, Long> {
    DomainCustomer createCustomer(String name, int age);
    boolean isSub(Long customerId);
}
