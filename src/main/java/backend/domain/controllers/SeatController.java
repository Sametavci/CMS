package backend.domain.controllers;

import backend.application.services.HallService;
import backend.application.services.SeatService;
import backend.domain.models.DomainCustomer;
import backend.domain.models.DomainSeat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/seats")
public class SeatController extends BaseController<DomainSeat, Long> {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        super(seatService);
        this.seatService = seatService;
    }

    @Override
    public Class<? extends BaseController<DomainSeat, Long>> getControllerClass() {
        return SeatController.class;
    }
    @GetMapping("/hall/{id}")
    public CollectionModel<EntityModel<DomainSeat>> getAllSeatsFromHall(@PathVariable Long id){
        List<EntityModel<DomainSeat>> entityModels = seatService.getAllSeatsFromHall(id)
                .stream()
                .map(D -> EntityModel.of(D,
                        linkTo(methodOn(getControllerClass()).findById((Long) D.getId())).withSelfRel()
                ))
                .toList();
        return CollectionModel.of(entityModels, linkTo(methodOn(getControllerClass()).findAll()).withRel("all-entities"));
    }
}
