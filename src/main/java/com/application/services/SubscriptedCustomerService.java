package com.application.services;

import com.domain.models.DomainSubscriptedCustomer;
import com.domain.ports.repositorys.ISubscriptionCustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptedCustomerService extends BaseService<DomainSubscriptedCustomer, Long> {
    private final ISubscriptionCustomerRepository subscriptionCustomerRepository;

    public SubscriptedCustomerService(ISubscriptionCustomerRepository subscriptionCustomerRepository) {
        super(subscriptionCustomerRepository);
        this.subscriptionCustomerRepository = subscriptionCustomerRepository;
    }

    public DomainSubscriptedCustomer createSubscribedCustomer(DomainSubscriptedCustomer subscriptedCustomer) {
        return subscriptionCustomerRepository.save(subscriptedCustomer);
    }

    @Override
    public Optional<DomainSubscriptedCustomer> findById(Long id) {
        return subscriptionCustomerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        subscriptionCustomerRepository.deleteById(id);
    }
}
