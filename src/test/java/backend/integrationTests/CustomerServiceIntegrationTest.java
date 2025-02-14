package backend.integrationTests;

import backend.application.services.CustomerService;
import backend.domain.models.DomainCustomer;
import backend.domain.ports.repositorys.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class CustomerServiceIntegrationTest {
    private List<DomainCustomer> customers = new ArrayList<>();
    @Autowired
    private ICustomerRepository iCustomerRepository;
    @Autowired
    private CustomerService customerService;

    @BeforeEach
    public void setUp(){
        for (int i = 1; i <= 100; i++) {
            DomainCustomer customer = new DomainCustomer();
            customer.setName("Customer" + i);
            customer.setSurname("Surname" + i);
            customer.setAge(18 + (i % 30)); // Yaşları 18-47 arasında olacak
            customer.setEmail("customer" + i + "@example.com");
            customer.setIsSub(i <= 20); // İlk 20 müşteri 'isSub = true', diğerleri false olacak
            DomainCustomer savedCustomer = iCustomerRepository.save(customer);
            customers.add(savedCustomer);
        }
    }

    @Test
    public void testGetAllSubs(){
        List<DomainCustomer> customerList = iCustomerRepository.findAll().stream().filter(DomainCustomer::getIsSub).toList();
        assertTrue(customerList.size() == customerService.getAllSubs().size()
                && customerService.getAllSubs().containsAll(customerList));
    }

    @Test
    public void TestMakeCustomerSub(){
        assertTrue(customerService
                .makeCustomerSub(customers
                                .get(21)
                                .getId(),
                        "umutavci03@gmail.com")
                .getIsSub());
    }
    @Test
    public void TestMakeSubCustomer(){
        assertFalse(customerService.makeSubCustomer(customers.get(1).getId()).getIsSub());
    }
}
