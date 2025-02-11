package backend.domain.controllers;

import backend.application.services.SeatService;
import backend.domain.models.DomainCustomer;
import backend.domain.models.DomainSeat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seats")
public class SeatController extends BaseController<DomainSeat, Long> {
    public SeatController(SeatService seatService) {
        super(seatService);
    }

    @Override
    public Class<? extends BaseController<DomainSeat, Long>> getControllerClass() {
        return SeatController.class;
    }
}
