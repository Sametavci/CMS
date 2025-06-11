package backend.integrationTests;

import backend.application.services.SeatService;
import backend.domain.models.DomainHall;
import backend.domain.models.DomainSeat;
import backend.domain.ports.repositorys.IHallRepository;
import backend.domain.ports.repositorys.ISeatRepository;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class SeatServiceIntegrationTest {

    List<DomainSeat> seats = new ArrayList<>();
    DomainHall hall;
    @Autowired
    IHallRepository iHallRepository;
    @Autowired
    ISeatRepository iSeatRepository;
    @Autowired
    SeatService seatService;


    @BeforeEach
    public void setUp(){
        hall = new DomainHall();
        hall.setName("IMAX Hall");
        hall.setCapacity(100);
        hall.setType("IMAX");
        hall = iHallRepository.save(hall);

        for (int i = 1; i <= 100; i++) {
            DomainSeat seat = new DomainSeat();
            seat.setSeatRow("A");
            seat.setSeatColumn(i);
            seat.setBooked(i <= 20);
            seat.setSeatType("Standard");
            seat.setHall(hall.getId());
            DomainSeat savedSeat = iSeatRepository.save(seat);
            seats.add(savedSeat);
        }
    }
    @AfterEach
    public void tearDown() {
        for (DomainSeat seat : iSeatRepository.findAll()) {
            iSeatRepository.deleteById(seat.getId());
        }

        for (DomainHall hall : iHallRepository.findAll()) {
            iHallRepository.deleteById(hall.getId());
        }
        seats.clear();

        System.out.println("Seat ve Hall test verileri temizlendi!");
    }

    @Test
    public void TestGetAllSeatsFromHall(){
        List<DomainSeat> domainSeats = seatService.getAllSeatsFromHall(hall.getId());
        seats = new ArrayList<>(seats.stream()
                .filter(m -> m.getHall() == hall.getId())
                .toList());
        assertTrue(domainSeats.size() == seats.size() && seats.containsAll(domainSeats));
    }


}