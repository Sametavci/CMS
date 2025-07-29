package backend.domain.controllers;

import backend.application.services.AuthService;
import backend.domain.models.DomainTicketClerk;
import backend.infrastructure.api.dto.AuthRequest;
import backend.infrastructure.persistence.entities.TicketClerk;
import backend.infrastructure.persistence.mapper.TicketClerkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/ticket-clerks")
@RequiredArgsConstructor
public class TicketClerkController {
    private final AuthService service;
    private final PasswordEncoder passwordEncoder;


    @GetMapping
    public CollectionModel<EntityModel<TicketClerk>> findAll(){
        List<EntityModel<TicketClerk>> entityModels = service.findAll()
                .stream()
                .map(D -> EntityModel.of(D,
                        linkTo(methodOn(TicketClerkController.class).findById(D.getId())).withSelfRel()
                ))
                .toList();
        return CollectionModel.of(entityModels, linkTo(methodOn(TicketClerkController.class).findAll()).withRel("all-entities"));
    }

    @GetMapping("/{id}")
    public EntityModel<TicketClerk> findById(@PathVariable Long id) {
        TicketClerk entity = service.findById(id).orElseThrow(
                () -> new RuntimeException("Entity with that id couldnt find " + id)
        );
        return EntityModel.of(entity,
                linkTo(methodOn(TicketClerkController.class).findById(id)).withSelfRel(),
                linkTo(methodOn(TicketClerkController.class).findAll()).withRel("all-entities")
        );
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        if (service.findByUsername(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is already a ticket clerk");
        }
        TicketClerk user = new TicketClerk();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        String role;
        if (request.getRole() != null) {
            role = request.getRole();
        } else if (request.getEmail().endsWith("@cms-admin.com")) {
            role = "ADMIN";
        } else {
            role = "TICKET_CLERK";
        }
        user.setRole(role);

        service.save(user);

        return ResponseEntity.ok("Registration Successfully!");
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
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
    public ResponseEntity<?> update(@RequestBody AuthRequest dto, @PathVariable Long id){
        boolean check = false;
        if(service.findById(id).isPresent()){
            check = true;
        }
        TicketClerk ticketClerk = new TicketClerk();
        ticketClerk.setId(id);
        ticketClerk.setPassword(passwordEncoder.encode(dto.getPassword()));
        ticketClerk.setEmail(dto.getEmail());
        TicketClerk updatedEntity = service.update(ticketClerk, id);
        EntityModel<TicketClerk> entityModel = EntityModel.of(
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
}
