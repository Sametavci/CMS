package backend.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketClerk extends BaseEntity {
    private String email;
    private String password;
}
