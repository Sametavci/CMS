package backend.infrastructure.persistence.repositorys;

import backend.infrastructure.persistence.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerJpaRepository extends BaseJpaRepository<Customer, Long> {
}
