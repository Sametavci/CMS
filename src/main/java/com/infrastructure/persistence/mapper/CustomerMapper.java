package com.infrastructure.persistence.mapper;

import com.domain.models.DomainCustomer;
import com.infrastructure.persistence.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements BaseMapper<Customer, DomainCustomer> {

    @Override
    public Customer toEntity(DomainCustomer domain) {
        if (domain == null) return null;
        Customer customer = new Customer();
        customer.setId(domain.getId());
        customer.setName(domain.getName());
        customer.setSurname(domain.getSurname());
        customer.setAge(domain.getAge());
        return customer;
    }

    @Override
    public DomainCustomer toDomain(Customer entity) {
        if (entity == null) return null;
        DomainCustomer domain = new DomainCustomer();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setSurname(entity.getSurname());
        domain.setAge(entity.getAge());
        return domain;
    }
}
