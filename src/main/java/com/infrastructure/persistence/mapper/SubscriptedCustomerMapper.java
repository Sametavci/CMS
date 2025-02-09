package com.infrastructure.persistence.mapper;

import com.domain.models.DomainSubscriptedCustomer;
import com.infrastructure.persistence.entities.SubscriptedCustomer;
import org.springframework.stereotype.Component;

@Component("persistenceSubscriptedCustomerMapper")
public class SubscriptedCustomerMapper implements BaseMapper<SubscriptedCustomer, DomainSubscriptedCustomer> {

    @Override
    public SubscriptedCustomer toEntity(DomainSubscriptedCustomer domain) {
        if (domain == null) return null;
        SubscriptedCustomer customer = new SubscriptedCustomer();
        customer.setId(domain.getId());
        customer.setMail(domain.getMail());
        customer.setAge(domain.getAge());
        return customer;
    }

    @Override
    public DomainSubscriptedCustomer toDomain(SubscriptedCustomer entity) {
        if (entity == null) return null;
        DomainSubscriptedCustomer domain = new DomainSubscriptedCustomer();
        domain.setId(entity.getId());
        domain.setMail(entity.getMail());
        domain.setAge(entity.getAge());
        return domain;
    }
}
