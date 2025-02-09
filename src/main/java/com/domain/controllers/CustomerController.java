package com.domain.controllers;

import com.application.services.CustomerService;
import com.domain.models.DomainCustomer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController extends BaseController<DomainCustomer, Long> {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super(customerService);
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<DomainCustomer> createCustomer(@RequestParam String name, @RequestParam int age) {
        return ResponseEntity.ok(customerService.createCustomer(name, age));
    }

    @GetMapping("/{id}/is-sub")
    public ResponseEntity<Boolean> isSub(@PathVariable("id") Long customerId) {
        return ResponseEntity.ok(customerService.isSub(customerId));
    }
}
