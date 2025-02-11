package backend.domain.controllers;

import backend.application.services.HallService;
import backend.domain.models.DomainCustomer;
import backend.domain.models.DomainHall;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController extends BaseController<DomainHall, Long> {
    private final HallService hallService;

    public HallController(HallService hallService) {
        super(hallService);
        this.hallService = hallService;
    }

    @GetMapping("/{id}/seats/full")
    public ResponseEntity<Boolean> isAllSeatsFull(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hallService.isAllSeatsFullByHallId(id));
    }

    @GetMapping("/{id}/seats/empty")
    public ResponseEntity<List<?>> showEmptySeats(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hallService.showEmptySeats(id));
    }
    @Override
    public Class<? extends BaseController<DomainHall, Long>> getControllerClass() {
        return HallController.class;
    }
}
