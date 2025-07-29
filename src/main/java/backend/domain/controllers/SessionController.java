package backend.domain.controllers;

import backend.application.services.SessionService;
import backend.domain.models.DomainSeat;
import backend.domain.models.DomainSession;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/sessions")
public class SessionController extends BaseController<DomainSession, Long> {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        super(sessionService);
        this.sessionService = sessionService;
    }

    @GetMapping("/{id}/seats")
    public ResponseEntity<List<DomainSeat>> getSeatsBySessionId(@PathVariable Long id) {
        List<DomainSeat> seats = sessionService.getSeatsBySessionId(id);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/{id}/session/end")
    public ResponseEntity<LocalDateTime> endTimeBySessionId(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.endTimeBySessionId(id));
    }

    @GetMapping("/hall/{id}")
    public CollectionModel<EntityModel<DomainSession>> getAllSessionsFromHall(@PathVariable Long id) {
        List<EntityModel<DomainSession>> entityModels = sessionService.getAllSessionsFromHall(id)
                .stream()
                .map(D -> EntityModel.of(D,
                        linkTo(methodOn(getControllerClass()).findById((Long) D.getId())).withSelfRel()))
                .toList();

        return CollectionModel.of(entityModels,
                linkTo(methodOn(getControllerClass()).findAll()).withRel("all-entities"));
    }

    @GetMapping("/movie/{id}")
    public CollectionModel<EntityModel<DomainSession>> getAllSessionsFromMovie(@PathVariable Long id) {
        List<EntityModel<DomainSession>> entityModels = sessionService.getAllSessionsFromMovie(id)
                .stream()
                .map(D -> EntityModel.of(D,
                        linkTo(methodOn(getControllerClass()).findById((Long) D.getId())).withSelfRel()))
                .toList();

        return CollectionModel.of(entityModels,
                linkTo(methodOn(getControllerClass()).findAll()).withRel("all-entities"));
    }

    @PostMapping("/{sessionId}/reserve")
    public ResponseEntity<?> reserveSeats(
            @PathVariable Long sessionId,
            @RequestBody List<Long> seatIds,
            @RequestParam(required = false) Long customerId
    ) {
        System.out.println("🔵 [RESERVE] SessionID: " + sessionId + ", seatIds: " + seatIds + ", customerId: " + customerId);

        try {
            sessionService.reserveSeats(sessionId, seatIds, customerId); // customerId null olabilir
            return ResponseEntity.ok("Rezervasyon başarılı");
        } catch (Exception e) {
            System.out.println("❌ Rezervasyon hatası: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rezervasyon başarısız: " + e.getMessage());
        }
    }


    @Override
    public Class<? extends BaseController<DomainSession, Long>> getControllerClass() {
        return SessionController.class;
    }
}
