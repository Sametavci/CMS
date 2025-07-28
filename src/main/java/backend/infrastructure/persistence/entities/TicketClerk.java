package backend.infrastructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketClerk extends BaseEntity {
    private String email;
    private String password;
    private String role;
}
