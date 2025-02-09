package com.application.services;

import com.domain.models.DomainCustomer;
import com.domain.ports.repositorys.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService extends BaseService<DomainCustomer, Long> {
    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    public DomainCustomer createCustomer(String name, int age) {
        DomainCustomer customer = new DomainCustomer();
        customer.setName(name);
        customer.setAge(age);
        return customerRepository.save(customer);
    }

    public boolean isSub(Long customerId) {
        return customerRepository.isSub(customerId);
    }

    @Override
    public List<DomainCustomer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<DomainCustomer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
