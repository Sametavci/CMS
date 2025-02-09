package com.domain.controllers;

import com.application.services.SubscriptedCustomerService;
import com.domain.models.DomainSubscriptedCustomer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscripted-customer")
public class SubscriptedCustomerController extends BaseController<DomainSubscriptedCustomer, Long> {
    public SubscriptedCustomerController(SubscriptedCustomerService service) {
        super(service);
    }
}
