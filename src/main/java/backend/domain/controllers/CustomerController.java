package backend.domain.controllers;

import backend.application.services.CustomerService;
import backend.domain.models.DomainCustomer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // TODO : SUB YAPMA KODLA MAIL + ID ALMAN LAZIM
}
