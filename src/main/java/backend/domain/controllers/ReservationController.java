package backend.domain.controllers;

import backend.application.services.ReservationService;
import backend.domain.models.DomainCustomer;
import backend.domain.models.DomainReservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController extends BaseController<DomainReservation, Long> {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        super(reservationService);
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}/discounted-price")
    public ResponseEntity<Double> calculateDiscountedPrice(@PathVariable("id") Long id,
                                                           @RequestParam("discountPercentage") Double discountPercentage) {
        return ResponseEntity.ok(reservationService.calculateDiscountedPrice(id, discountPercentage));
    }

    @GetMapping("/{id}/eligibility")
    public ResponseEntity<Boolean> checkCustomerEligibility(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reservationService.checkCustomerDiscountEligibility(id));
    }
    @Override
    public Class<? extends BaseController<DomainReservation, Long>> getControllerClass() {
        return ReservationController.class;
    }
}
