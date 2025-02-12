package backend.infrastructure.persistence.adapter;

import backend.infrastructure.persistence.entities.Customer;
import backend.infrastructure.persistence.mapper.CustomerMapper;
import backend.infrastructure.persistence.repositorys.ICustomerJpaRepository;
import backend.domain.models.DomainCustomer;
import backend.domain.ports.repositorys.ICustomerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
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
    public boolean isSub(Long customerId) {
        return customerJpaRepository.existsById(customerId);
    }

    @Override
    public List<DomainCustomer> findAll() {
        return customerJpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<DomainCustomer> findById(Long id) {
        return customerJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        customerJpaRepository.deleteById(id);
    }

    public DomainCustomer update(DomainCustomer dto, Long id){
        DomainCustomer databaseElement = findById(id).orElseThrow(
                () -> new RuntimeException("Entity with that id couldnt find" + id)
        );
        Customer entity = mapper.toEntity(databaseElement);
        mapper.update(entity, dto);
        Customer savedEntity = customerJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    public List<DomainCustomer> getAllSubs(){
        return Optional.ofNullable(findAll())
                .orElse(Collections.emptyList())
                .stream()
                .filter(customer -> Boolean.TRUE.equals(customer.getIsSub()))
                .toList();
    }
    public DomainCustomer makeCustomerSub(Long id, String mail){
        DomainCustomer databaseElement = findById(id).orElseThrow(
                () -> new RuntimeException("Entity with that id couldnt find" + id)
        );
        Customer entity = mapper.toEntity(databaseElement);
        databaseElement.setIsSub(true);
        databaseElement.setEmail(mail);
        mapper.update(entity, databaseElement);
        Customer savedEntity = customerJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    public DomainCustomer makeSubCustomer(Long id){
        DomainCustomer databaseElement = findById(id).orElseThrow(
                () -> new RuntimeException("Entity with that id couldnt find" + id)
        );
        Customer entity = mapper.toEntity(databaseElement);
        databaseElement.setIsSub(false);
        databaseElement.setEmail(null);
        mapper.update(entity, databaseElement);
        Customer savedEntity = customerJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}
