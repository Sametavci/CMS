package backend.domain.controllers;

import backend.application.services.BaseService;
import backend.domain.models.DomainBase;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public abstract class BaseController<T extends DomainBase, ID extends Serializable> {

    private final BaseService<T, ID> service;

    public BaseController(BaseService<T, ID> service) {
        this.service = service;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') || hasRole('TICKET_CLERK')")
    public CollectionModel<EntityModel<T>> findAll() {
        List<EntityModel<T>> entityModels = service.findAll()
                .stream()
                .map(D -> EntityModel.of(D,
                        linkTo(methodOn(getControllerClass()).findById((ID) D.getId())).withSelfRel()
                ))
                .toList();
        return CollectionModel.of(entityModels, linkTo(methodOn(getControllerClass()).findAll()).withRel("all-entities"));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('TICKET_CLERK')")
    public EntityModel<T> findById(@PathVariable ID id) {
        T entity = service.findById(id).orElseThrow(
                () -> new RuntimeException("Entity with that id couldnt find " + id)
        );
        return EntityModel.of(entity,
                linkTo(methodOn(getControllerClass()).findById(id)).withSelfRel(),
                linkTo(methodOn(getControllerClass()).findAll()).withRel("all-entities")
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('TICKET_CLERK')")
    public ResponseEntity<T> save(@RequestBody T dto) {
        T savedDto = service.save(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/"+savedDto.getId())
                .buildAndExpand(savedDto.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('TICKET_CLERK')")
    public ResponseEntity<Void> deleteById(@PathVariable ID id) {
        service.deleteById(id);
        return (ResponseEntity<Void>) getResponseQuery();
    }
    public ResponseEntity<?> getResponseQuery() {
        String requestedValue = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().getPath();
        while(requestedValue.charAt(requestedValue.length()-1) != '/'){
            requestedValue = requestedValue.substring(0, requestedValue.length()-1);
        }
        URI collectionUri = ServletUriComponentsBuilder.fromCurrentServletMapping()
                .path(requestedValue)
                .build()
                .toUri();
        return ResponseEntity.noContent().location(collectionUri).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('TICKET_CLERK')")
    public ResponseEntity<?> update(@RequestBody T dto, @PathVariable ID id){
        boolean check = false;
        if(service.findById(id).isPresent()){
            check = true;
        }
        T updatedEntity = service.update(dto, id);
        EntityModel<T> entityModel = EntityModel.of(
                updatedEntity,
                linkTo(methodOn(getClass()).update(dto, id)).withSelfRel(), // Self link
                linkTo(methodOn(getClass()).findAll()).withRel("all-entities"));
        if(!check){
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }
        else{
            return getResponseQuery();
        }

        // TODO : CREATE 201, DELETE 204 (Sanirim düzeldi)
    }

    public abstract Class<? extends BaseController<T, ID>> getControllerClass();

}
