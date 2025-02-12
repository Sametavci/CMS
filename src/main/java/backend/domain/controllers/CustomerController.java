package backend.domain.controllers;

import backend.application.services.CustomerService;
import backend.domain.models.DomainCustomer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/customers")
public class CustomerController extends BaseController<DomainCustomer, Long> {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super(customerService);
        this.customerService = customerService;
    }

    @GetMapping("/{id}/is-sub")
    public ResponseEntity<Boolean> isSub(@PathVariable("id") Long customerId) {
        return ResponseEntity.ok(customerService.isSub(customerId));
    }
    @Override
    public Class<? extends BaseController<DomainCustomer, Long>> getControllerClass() {
        return CustomerController.class;
    }
    @Override
    public ResponseEntity<DomainCustomer> getResponseQuery() {
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

    @PostMapping("{id}/customerToSub")
    public ResponseEntity<DomainCustomer> makeCustomerSub(@PathVariable Long id, @RequestBody String email){
        DomainCustomer updatedEntity = customerService.makeCustomerSub(id, email);
        EntityModel<DomainCustomer> entityModel = EntityModel.of(
                updatedEntity,
                linkTo(methodOn(getClass()).makeCustomerSub(id, email)).withSelfRel(), // Self link
                linkTo(methodOn(getClass()).findAll()).withRel("all-entities"));
        return getResponseQuery();
    }
    @PostMapping("{id}/subToCustomer")
    public ResponseEntity<DomainCustomer> makeSubCustomer(@PathVariable Long id){
        DomainCustomer updatedEntity = customerService.makeSubCustomer(id);
        EntityModel<DomainCustomer> entityModel = EntityModel.of(
                updatedEntity,
                linkTo(methodOn(getClass()).makeSubCustomer(id)).withSelfRel(), // Self link
                linkTo(methodOn(getClass()).findAll()).withRel("all-entities"));
        return getResponseQuery();
    }
    @GetMapping("subs")
    public CollectionModel<EntityModel<DomainCustomer>> getAllSubs(){
        List<EntityModel<DomainCustomer>> entityModels = customerService.getAllSubs()
                .stream()
                .map(D -> EntityModel.of(D,
                        linkTo(methodOn(getControllerClass()).findById((Long) D.getId())).withSelfRel()
                ))
                .toList();
        return CollectionModel.of(entityModels, linkTo(methodOn(getControllerClass()).findAll()).withRel("all-entities"));
    }

}
