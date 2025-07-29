package backend.domain.controllers;

import backend.application.services.HallService;
import backend.domain.models.DomainHall;
import backend.domain.models.DomainSeat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api/halls")
public class HallController extends BaseController<DomainHall, Long> {

    private final HallService hallService;

    public HallController(HallService hallService) {
        super(hallService);
        this.hallService = hallService;
    }
    @Override
    public ResponseEntity<DomainHall> save(@RequestBody DomainHall hall) {
        DomainHall saved = hallService.createHallWithSeats(hall);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }


    @GetMapping("/{id}/seats/full")
    public ResponseEntity<Boolean> isAllSeatsFull(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hallService.isAllSeatsFullByHallId(id));
    }

    @GetMapping("/{id}/seats/empty")
    public ResponseEntity<List<DomainSeat>> showEmptySeats(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hallService.showEmptySeats(id));
    }

    @Override
    public Class<? extends BaseController<DomainHall, Long>> getControllerClass() {
        return HallController.class;
    }
}
