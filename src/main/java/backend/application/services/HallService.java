package backend.application.services;

import backend.domain.models.DomainHall;
import backend.domain.models.DomainSeat;
import backend.domain.ports.repositorys.IHallRepository;
import backend.domain.ports.repositorys.ISeatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HallService extends BaseService<DomainHall, Long> {
    private final IHallRepository hallRepository;
    private final ISeatRepository seatRepository;

    public HallService(IHallRepository hallRepository, ISeatRepository seatRepository) {
        super(hallRepository);
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
    }

    public DomainHall createHallWithSeats(DomainHall hall) {
        DomainHall savedHall = hallRepository.save(hall);

        int capacity = savedHall.getCapacity();
        int seatsPerRow = 10;
        int rowCount = (int) Math.ceil((double) capacity / seatsPerRow);

        List<DomainSeat> seats = new ArrayList<>();

        for (int row = 0; row < rowCount; row++) {
            char rowLetter = (char) ('A' + row);
            for (int col = 1; col <= seatsPerRow; col++) {
                int seatNumber = row * seatsPerRow + col;
                if (seatNumber > capacity) break;

                DomainSeat seat = new DomainSeat();
                seat.setSeatRow(String.valueOf(rowLetter));
                seat.setSeatColumn(col);
                seat.setHall(savedHall.getId());
                seat.setSeatType("NORMAL");
                seat.setBooked(false);
                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);
        return savedHall;
    }

    public boolean isAllSeatsFullByHallId(Long id) {
        return hallRepository.isAllSeatsFullByHallId(id);
    }

    public List<DomainSeat> showEmptySeats(Long id) {
        return hallRepository.showEmptySeats(id);
    }
}
