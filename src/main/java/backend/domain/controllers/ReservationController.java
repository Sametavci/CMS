package backend.domain.controllers;

import backend.application.services.ReservationService;
import backend.domain.models.DomainReservation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController extends BaseController<DomainReservation, Long> {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        super(reservationService);
        this.reservationService = reservationService;
    }

    @Override
    public Class<? extends BaseController<DomainReservation, Long>> getControllerClass() {
        return ReservationController.class;
    }
}
