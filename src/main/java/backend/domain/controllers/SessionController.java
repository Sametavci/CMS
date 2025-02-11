package backend.domain.controllers;

import backend.application.services.SessionService;
import backend.domain.models.DomainCustomer;
import backend.domain.models.DomainSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sessions")
public class SessionController extends BaseController<DomainSession, Long> {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        super(sessionService);
        this.sessionService = sessionService;
    }

    @GetMapping("/{id}/session/end")
    public ResponseEntity<LocalDateTime> endTimeBySessionId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sessionService.endTimeBySessionId(id));
    }
    @Override
    public Class<? extends BaseController<DomainSession, Long>> getControllerClass() {
        return SessionController.class;
    }
}
