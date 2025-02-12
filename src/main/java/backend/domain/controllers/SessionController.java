package backend.domain.controllers;

import backend.application.services.SessionService;
import backend.domain.models.DomainCustomer;
import backend.domain.models.DomainSeat;
import backend.domain.models.DomainSession;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

    @GetMapping("/{id}/session/end")
    public ResponseEntity<LocalDateTime> endTimeBySessionId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sessionService.endTimeBySessionId(id));
    }

    @GetMapping("hall/{id}")
    public CollectionModel<EntityModel<DomainSession>> getAllSessionsFromHall(@PathVariable Long id){
        List<EntityModel<DomainSession>> entityModels = sessionService.getAllSessionsFromHall(id)
                .stream()
                .map(D -> EntityModel.of(D,
                        linkTo(methodOn(getControllerClass()).findById((Long) D.getId())).withSelfRel()
                ))
                .toList();
        return CollectionModel.of(entityModels, linkTo(methodOn(getControllerClass()).findAll()).withRel("all-entities"));
    }

    @GetMapping("movie/{id}")
    public CollectionModel<EntityModel<DomainSession>> getAllSessionsFromMovie(@PathVariable Long id){
        List<EntityModel<DomainSession>> entityModels = sessionService.getAllSessionsFromMovie(id)
                .stream()
                .map(D -> EntityModel.of(D,
                        linkTo(methodOn(getControllerClass()).findById((Long) D.getId())).withSelfRel()
                ))
                .toList();
        return CollectionModel.of(entityModels, linkTo(methodOn(getControllerClass()).findAll()).withRel("all-entities"));
    }

    @Override
    public Class<? extends BaseController<DomainSession, Long>> getControllerClass() {
        return SessionController.class;
    }
}
