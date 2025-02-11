package backend.infrastructure.persistence.mapper;

import backend.infrastructure.persistence.entities.Customer;
import backend.domain.models.DomainCustomer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("persistenceCustomerMapper")
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
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    @Override
    public void update(Customer entity, DomainCustomer dto) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
