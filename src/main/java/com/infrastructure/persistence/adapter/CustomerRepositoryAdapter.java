package com.infrastructure.persistence.adapter;

import com.domain.models.DomainCustomer;
import com.domain.ports.repositorys.ICustomerRepository;
import com.infrastructure.persistence.mapper.CustomerMapper;
import com.infrastructure.persistence.entities.Customer;
import com.infrastructure.persistence.repositorys.ICustomerJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryAdapter implements ICustomerRepository {

    private final ICustomerJpaRepository customerJpaRepository;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerRepositoryAdapter(ICustomerJpaRepository customerJpaRepository, CustomerMapper mapper) {
        this.customerJpaRepository = customerJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DomainCustomer save(DomainCustomer entity) {
        Customer customerEntity = mapper.toEntity(entity);
        Customer savedEntity = customerJpaRepository.save(customerEntity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public DomainCustomer createCustomer(String name, int age) {
        DomainCustomer domainCustomer = new DomainCustomer();
        domainCustomer.setName(name);
        domainCustomer.setAge(age);

        Customer customerEntity = mapper.toEntity(domainCustomer);
        Customer savedEntity = customerJpaRepository.save(customerEntity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public boolean isSub(Long customerId) {
        return customerJpaRepository.existsById(customerId);
    }

    @Override
    public List<DomainCustomer> findAll() {
        return null;
    }

    @Override
    public Optional<DomainCustomer> findById(Long id) {
        return customerJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        customerJpaRepository.deleteById(id);
    }
}
