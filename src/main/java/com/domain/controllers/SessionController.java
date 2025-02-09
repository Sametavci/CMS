package com.domain.controllers;

import com.application.services.SessionService;
import com.domain.models.DomainSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/session")
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
}
