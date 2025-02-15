package backend.application.services;

import backend.domain.models.DomainReservation;
import backend.domain.ports.repositorys.IReservationRepository;
import org.springframework.stereotype.Service;


@Service
public class ReservationService extends BaseService<DomainReservation, Long> {
    private final IReservationRepository reservationRepository;

    public ReservationService(IReservationRepository reservationRepository) {
        super(reservationRepository);
        this.reservationRepository = reservationRepository;
    }

}
