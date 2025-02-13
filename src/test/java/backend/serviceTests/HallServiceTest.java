package backend.serviceTests;

import backend.domain.models.DomainHall;
import backend.domain.models.DomainSeat;
import backend.domain.ports.repositorys.IHallRepository;
import backend.application.services.HallService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HallServiceTest {

    @Mock
    private IHallRepository hallRepository;
    @InjectMocks
    private HallService hallService;

    private DomainHall hall;
    private DomainSeat seat1, seat2;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hall = new DomainHall("Example Hall", 50, "3D");
        seat1 = new DomainSeat("A", 1, false, 1L, "Normal");
        seat2 = new DomainSeat("A", 1, true, 1L, "Normal");
    }

    @Test
    void testGetHallById() {
        when(hallRepository.findById(1L)).thenReturn(Optional.of(hall));

        Optional<DomainHall> result = hallService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("3D", hall.getType());
    }

    @Test
    void testSaveHall() {
        when(hallRepository.save(any(DomainHall.class))).thenReturn(hall);
        DomainHall savedHall = hallService.save(hall);
        assertNotNull(savedHall);
        assertEquals("Example Hall", savedHall.getName());
    }

    @Test
    void testIsAllSeatsFullByHallId_WhenAllSeatsFull() {
        when(hallRepository.isAllSeatsFullByHallId(1L)).thenReturn(true);

        boolean result = hallService.isAllSeatsFullByHallId(1L);
        assertTrue(result);
    }


    @Test
    void testIsAllSeatsFullByHallId_WhenNotAllSeatsFull() {
        when(hallRepository.isAllSeatsFullByHallId(1L)).thenReturn(false);
        boolean result = hallService.isAllSeatsFullByHallId(1L);
        assertFalse(result);
    }

}