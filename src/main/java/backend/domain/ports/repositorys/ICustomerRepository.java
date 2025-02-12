package backend.domain.ports.repositorys;

import backend.domain.models.DomainCustomer;

import java.util.List;

public interface ICustomerRepository extends BaseRepository<DomainCustomer, Long> {
    boolean isSub(Long customerId);
    public DomainCustomer makeSubCustomer(Long id);
    public DomainCustomer makeCustomerSub(Long id, String mail);
    public List<DomainCustomer> getAllSubs();
}
