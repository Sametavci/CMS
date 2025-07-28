package backend.integrationTests;

import backend.application.services.HallService;
import backend.domain.models.DomainHall;
import backend.domain.models.DomainSeat;
import backend.domain.ports.repositorys.IHallRepository;
import backend.domain.ports.repositorys.ISeatRepository;
import backend.infrastructure.persistence.entities.Hall;
import backend.infrastructure.persistence.entities.Seat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class HallServiceIntegrationTest {

    private DomainHall hall;
    private List<DomainSeat> list = new ArrayList<>();
    @Autowired
    private HallService hallService;

    @Autowired
    private IHallRepository hallRepository;

    @Autowired
    private ISeatRepository seatRepository;


    @BeforeEach
    void setUp() {
        hall = new DomainHall();
        hall.setName("IMAX Hall");
        hall.setCapacity(100);
        hall.setType("IMAX");
        hall = hallRepository.save(hall);

        for (int i = 1; i <= 100; i++) {
            DomainSeat seat = new DomainSeat();
            seat.setSeatRow("A");
            seat.setSeatColumn(i);
            seat.setBooked(false);
            seat.setSeatType("Standard");
            seat.setHall(hall.getId());
            DomainSeat savedSeat = seatRepository.save(seat);
            list.add(savedSeat);
        }
    }
    @AfterEach
    void tearDown() {
        for (DomainSeat seat : seatRepository.findAll()) {
            seatRepository.deleteById(seat.getId());
        }

        for (DomainHall hall : hallRepository.findAll()) {
            hallRepository.deleteById(hall.getId());
        }

        list.clear();
    }
    @Test
    void testIsAllSeatsFullByHallId_WithRealDatabase() {
        boolean result = hallService.isAllSeatsFullByHallId(hall.getId());
        assertFalse(result);
    }

    @Test
    void testShowEmptySeatsWithRealDatabase() {
        List<DomainSeat> list1 = hallService.showEmptySeats(hall.getId());
        List<DomainSeat> list2 = list.stream().filter(m -> !m.isBooked()).toList();

        assertEquals(true, list1.size() == list2.size() && list1.containsAll(list2));
    }

}
