package backend.serviceTests;


import backend.application.services.SeatService;
import backend.domain.models.DomainHall;
import backend.domain.models.DomainSeat;
import backend.domain.ports.repositorys.ISeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeatServiceTest {

    @Mock
    private ISeatRepository seatRepository;

    @InjectMocks
    private SeatService seatService;

    private DomainSeat seat1, seat2;
    private DomainHall hall;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hall = new DomainHall("Hall 1", 50, "IMAX");
        seat1 = new DomainSeat("A", 1, false, hall.getId(), "Regular" );
        seat2 = new DomainSeat("A", 2, true, hall.getId(), "Regular");
    }

    @Test
    void testGetAllSeatsFromHall() {
        Long hallId = 1L;
        List<DomainSeat> expectedSeats = Arrays.asList(seat1, seat2);

        when(seatRepository.getAllSeatsFromHall(hallId)).thenReturn(expectedSeats);

        List<DomainSeat> result = seatService.getAllSeatsFromHall(hallId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getSeatRow());
        verify(seatRepository, times(1)).getAllSeatsFromHall(hallId);
    }
}
