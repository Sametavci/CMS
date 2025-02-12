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
        customer.setEmail(domain.getEmail());
        customer.setIsSub(domain.getIsSub());
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
        domain.setIsSub(entity.getIsSub());
        domain.setEmail(entity.getEmail());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    @Override
    public void update(Customer entity, DomainCustomer dto) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setIsSub(dto.getIsSub());
        entity.setEmail(dto.getEmail());
        entity.setSurname(dto.getSurname());
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
