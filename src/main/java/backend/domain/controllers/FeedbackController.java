package backend.domain.controllers;

import backend.application.services.FeedbackService;
import backend.infrastructure.persistence.entities.Feedback;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService service;

    @GetMapping
    public CollectionModel<EntityModel<Feedback>> findAll() {
        List<EntityModel<Feedback>> feedbacks = service.findAll().stream()
                .map(f -> EntityModel.of(f,
                        linkTo(methodOn(FeedbackController.class).findById(f.getId())).withSelfRel()))
                .toList();

        return CollectionModel.of(feedbacks,
                linkTo(methodOn(FeedbackController.class).findAll()).withRel("all-feedbacks"));
    }

    @GetMapping("/{id}")
    public EntityModel<Feedback> findById(@PathVariable Long id) {
        Feedback feedback = service.findById(id).orElseThrow(
                () -> new RuntimeException("Feedback with id " + id + " not found.")
        );
        return EntityModel.of(feedback,
                linkTo(methodOn(FeedbackController.class).findById(id)).withSelfRel(),
                linkTo(methodOn(FeedbackController.class).findAll()).withRel("all-feedbacks"));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Feedback feedback) {
        service.save(feedback);
        return ResponseEntity.ok("Feedback created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Feedback updatedFeedback, @PathVariable Long id) {
        boolean exists = service.findById(id).isPresent();

        Feedback savedFeedback = service.update(updatedFeedback, id);
        EntityModel<Feedback> entityModel = EntityModel.of(savedFeedback,
                linkTo(methodOn(FeedbackController.class).update(updatedFeedback, id)).withSelfRel(),
                linkTo(methodOn(FeedbackController.class).findAll()).withRel("all-feedbacks"));

        if (!exists) {
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        } else {
            return getResponseQuery();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return (ResponseEntity<Void>) getResponseQuery();
    }

    @GetMapping("/count")
    public Long getCount(){
        return service.getCount();
    }

    public ResponseEntity<?> getResponseQuery() {
        String requestedValue = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().getPath();
        while (requestedValue.charAt(requestedValue.length() - 1) != '/') {
            requestedValue = requestedValue.substring(0, requestedValue.length() - 1);
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping()
                .path(requestedValue)
                .build()
                .toUri();
        return ResponseEntity.noContent().location(uri).build();
    }

}

