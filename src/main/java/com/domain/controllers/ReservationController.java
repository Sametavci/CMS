package com.domain.controllers;

import com.application.services.ReservationService;
import com.domain.models.DomainReservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
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
}
