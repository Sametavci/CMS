package backend.serviceTests;


import backend.domain.models.DomainReservation;
import backend.domain.ports.repositorys.IReservationRepository;
import backend.application.services.ReservationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private IReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private DomainReservation reservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reservation = new DomainReservation(1L, 2L, 3L);
    }

   @Test
    void testGetReservationById() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        Optional<DomainReservation> result = reservationService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(reservation.getSession(), result.get().getSession());
    }

    @Test
    void testSaveReservation() {
        when(reservationRepository.save(any(DomainReservation.class))).thenReturn(reservation);
        DomainReservation savedReservation = reservationService.save(reservation);
        assertNotNull(savedReservation);
        assertEquals(3L, savedReservation.getCustomer());
    }
}
