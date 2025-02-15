package backend.integrationTests;

import backend.application.services.CustomerService;
import backend.domain.models.DomainCustomer;
import backend.domain.ports.repositorys.ICustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
            customer.setAge(18 + (i % 30));
            customer.setEmail("customer" + i + "@example.com");
            customer.setIsSub(i <= 20);
            DomainCustomer savedCustomer = iCustomerRepository.save(customer);
            customers.add(savedCustomer);
        }
    }
    @AfterEach
    public void tearDown() {
        for (DomainCustomer customer : iCustomerRepository.findAll()) {
            iCustomerRepository.deleteById(customer.getId());
        }

        customers.clear();

        System.out.println("Customer test verileri temizlendi!");
    }

    @Test
    public void TestCreate(){
        assertNotNull(customerService.findById(customers.get(1).getId()));
    }
    @Test
    public void TestGet(){
        assertEquals(customers.get(1).getId(), customerService.findById(customers.get(1).getId()).get().getId());
    }
    @Test
    public void TestDelete(){
        customerService.deleteById(customers.get(3).getId());
        assertEquals(Optional.empty(),customerService.findById(customers.get(3).getId()));
    }
    @Test
    public void TestUpdate() {
        customers.get(4).setName("TEST");
        customerService.update(customers.get(4), customers.get(4).getId());
        String name = customerService.findById(customers.get(4).getId()).get().getName();
        assertEquals("TEST", name);

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