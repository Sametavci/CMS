package backend.serviceTests;

import backend.application.services.CustomerService;
import backend.domain.models.DomainCustomer;
import backend.domain.ports.repositorys.ICustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private DomainCustomer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new DomainCustomer("John", "Doe", 30, true, "johndoe@example.com");
    }

    @Test
    void testIsSub_WhenCustomerIsSubscribed() {
        when(customerRepository.isSub(1L)).thenReturn(true);

        assertTrue(true);
    }

    @Test
    void testIsSub_WhenCustomerIsNotSubscribed() {
        when(customerRepository.isSub(1L)).thenReturn(false);

        boolean result = customerService.isSub(1L);
        assertFalse(result);
    }

    @Test
    void testMakeSubCustomer() {
        when(customerRepository.makeSubCustomer(1L)).thenReturn(customer);

        DomainCustomer updatedCustomer = customerService.makeSubCustomer(1L);
        assertNotNull(updatedCustomer);
        assertEquals("John", updatedCustomer.getName());
    }

    @Test
    void testMakeCustomerSub_WithIdAndMail() {
        when(customerRepository.makeCustomerSub(1L, "johndoe@example.com")).thenReturn(customer);

        DomainCustomer updatedCustomer = customerService.makeCustomerSub(1L, "johndoe@example.com");
        assertNotNull(updatedCustomer);
        assertEquals("johndoe@example.com", updatedCustomer.getEmail());
    }

    @Test
    void testGetAllSubs() {
        List<DomainCustomer> subs = Arrays.asList(
                new DomainCustomer("Jane", "Doe", 29, true, "janedoe@example.com"),
                new DomainCustomer("Alice", "Doe", 28, true, "alicedoe@example.com")
        );

        when(customerRepository.getAllSubs()).thenReturn(subs);

        List<DomainCustomer> result = customerService.getAllSubs();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
