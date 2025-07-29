package backend.application.services;

import backend.domain.models.DomainHall;
import backend.domain.models.DomainSeat;
import backend.domain.ports.repositorys.ISeatRepository;
import backend.domain.ports.repositorys.ISessionRepository;
import backend.domain.models.DomainSession;
import backend.infrastructure.persistence.entities.Seat;
import backend.infrastructure.persistence.entities.Session;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService extends BaseService<DomainSession, Long> {
    private final ISessionRepository sessionRepository;
private final ISeatRepository seatRepository;
    public SessionService(ISessionRepository sessionRepository, ISeatRepository seatRepository) {
        super(sessionRepository);
        this.sessionRepository = sessionRepository;
        this.seatRepository = seatRepository;
    }


    public LocalDateTime endTimeBySessionId(Long sessionId) {
        return sessionRepository.endTimeBySessionId(sessionId);
    }

    public List<DomainSession> getAllSessionsFromHall(Long hallId) { return sessionRepository.getAllSessionsFromHall(hallId); }
    public List<DomainSession> getAllSessionsFromMovie(Long movieId) { return sessionRepository.getAllSessionsFromMovie(movieId); }
    public List<DomainSeat> getSeatsBySessionId(Long sessionId) {
        DomainSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        Long hallId = session.getHall();

        return seatRepository.getAllSeatsFromHall(hallId);
    }
    @Transactional
    public void reserveSeats(Long sessionId, List<Long> seatIds) {
        DomainSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found: " + sessionId));

        Long sessionHallId = session.getHall();

        List<DomainSeat> seatsToReserve = seatRepository.findAllById(seatIds);

        for (DomainSeat seat : seatsToReserve) {
            if (!seat.getHall().equals(sessionHallId)) {
                throw new RuntimeException("Seat " + seat.getId() + " is not part of hall " + sessionHallId);
            }

            // Zaten rezerve mi?
            if (seat.isBooked()) {
                throw new RuntimeException("Seat already booked: " + seat.getId());
            }

            seat.setBooked(true);
            seatRepository.save(seat);
        }
    }



}
