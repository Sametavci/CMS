package backend.infrastructure.persistence.mapper;

import backend.domain.models.DomainTicketClerk;
import backend.infrastructure.persistence.entities.TicketClerk;
import org.springframework.stereotype.Component;

@Component("ticketClerkMapper")
public class TicketClerkMapper {
    public TicketClerk toEntity(DomainTicketClerk domainTicketClerk){
        TicketClerk ticketClerk = new TicketClerk();
        ticketClerk.setEmail(domainTicketClerk.getEmail());
        ticketClerk.setPassword(domainTicketClerk.getPassword());
        return ticketClerk;
    }
    public DomainTicketClerk toDomain(TicketClerk ticketClerk){
        DomainTicketClerk domainTicketClerk = new DomainTicketClerk();
        domainTicketClerk.setEmail(ticketClerk.getEmail());
        domainTicketClerk.setPassword(ticketClerk.getPassword());
        return domainTicketClerk;
    }
}
