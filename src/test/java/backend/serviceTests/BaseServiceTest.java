package backend.serviceTests;

import backend.application.services.BaseService;
import backend.domain.models.DomainCustomer;
import backend.domain.ports.repositorys.BaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class BaseServiceTest {

    @Mock
    private BaseRepository<DomainCustomer, Long> baseRepository;

    private BaseService<DomainCustomer, Long> baseService;

    private DomainCustomer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        baseService = new BaseService<>(baseRepository) {};

        customer = new DomainCustomer("John", "Doe", 30, true, "johndoe@example.com");
    }

    @Test
    void testFindById_WhenExists() {
        when(baseRepository.findById(1L)).thenReturn(Optional.of(customer));

        Optional<DomainCustomer> result = baseService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
    }

    @Test
    void testFindById_WhenNotExists() {
        when(baseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<DomainCustomer> result = baseService.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        when(baseRepository.save(any(DomainCustomer.class))).thenReturn(customer);

        DomainCustomer savedCustomer = baseService.save(customer);
        assertNotNull(savedCustomer);
        assertEquals("John", savedCustomer.getName());
    }

    @Test
    void testUpdate() {
        when(baseRepository.save(any(DomainCustomer.class))).thenReturn(customer);

        DomainCustomer updatedCustomer = baseService.update(customer, customer.getId());

        assertNotNull(updatedCustomer);
        assertEquals("John", updatedCustomer.getName());
    }

    @Test
    void testDeleteById() {
        doNothing().when(baseRepository).deleteById(1L);

        assertDoesNotThrow(() -> baseService.deleteById(1L));
        verify(baseRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindAll() {
        List<DomainCustomer> customers = Arrays.asList(
                new DomainCustomer("Jane", "Doe", 29, true, "janedoe@example.com"),
                new DomainCustomer("Alice", "Doe", 28, true, "alicedoe@example.com")
        );

        when(baseRepository.findAll()).thenReturn(customers);

        List<DomainCustomer> result = baseService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}