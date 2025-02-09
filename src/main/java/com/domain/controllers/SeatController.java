package com.domain.controllers;

import com.application.services.SeatService;
import com.domain.models.DomainSeat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seat")
public class SeatController extends BaseController<DomainSeat, Long> {
    public SeatController(SeatService seatService) {
        super(seatService);
    }
}
