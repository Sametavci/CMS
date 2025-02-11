package backend.domain.ports.repositorys;

import backend.domain.models.DomainCustomer;

public interface ICustomerRepository extends BaseRepository<DomainCustomer, Long> {
    boolean isSub(Long customerId);
}
