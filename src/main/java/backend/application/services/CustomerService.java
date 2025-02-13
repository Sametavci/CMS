package backend.application.services;

import backend.domain.models.DomainCustomer;
import backend.domain.models.DomainReservation;
import backend.domain.ports.repositorys.ICustomerRepository;
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
    public DomainCustomer makeSubCustomer(Long id) { return customerRepository.makeSubCustomer(id); }
    public DomainCustomer makeCustomerSub(Long id, String mail) { return customerRepository.makeCustomerSub(id, mail); }
    public List<DomainCustomer> getAllSubs() { return customerRepository.getAllSubs(); }
}
