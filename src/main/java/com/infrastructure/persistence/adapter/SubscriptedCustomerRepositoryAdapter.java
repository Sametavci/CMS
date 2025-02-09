package com.infrastructure.persistence.adapter;

import com.domain.models.DomainSubscriptedCustomer;
import com.domain.ports.repositorys.ISubscriptionCustomerRepository;
import com.infrastructure.persistence.entities.SubscriptedCustomer;
import com.infrastructure.persistence.mapper.SubscriptedCustomerMapper;
import com.infrastructure.persistence.repositorys.ISubscriptionCustomerJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SubscriptedCustomerRepositoryAdapter implements ISubscriptionCustomerRepository {

    private final ISubscriptionCustomerJpaRepository subscriptionCustomerJpaRepository;
    private final SubscriptedCustomerMapper mapper;

    @Autowired
    public SubscriptedCustomerRepositoryAdapter(ISubscriptionCustomerJpaRepository subscriptionCustomerJpaRepository,
                                                SubscriptedCustomerMapper mapper) {
        this.subscriptionCustomerJpaRepository = subscriptionCustomerJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DomainSubscriptedCustomer save(DomainSubscriptedCustomer entity) {
        SubscriptedCustomer customerEntity = mapper.toEntity(entity);
        SubscriptedCustomer savedEntity = subscriptionCustomerJpaRepository.save(customerEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<DomainSubscriptedCustomer> findById(Long id) {
        return subscriptionCustomerJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DomainSubscriptedCustomer> findAll() {
        return subscriptionCustomerJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        subscriptionCustomerJpaRepository.deleteById(id);
    }

    public DomainSubscriptedCustomer createSubscribedCustomer(DomainSubscriptedCustomer subscriptedCustomer) {
        SubscriptedCustomer customerEntity = mapper.toEntity(subscriptedCustomer);
        SubscriptedCustomer savedEntity = subscriptionCustomerJpaRepository.save(customerEntity);
        return mapper.toDomain(savedEntity);
    }
}
