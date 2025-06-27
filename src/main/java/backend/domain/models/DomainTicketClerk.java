package backend.domain.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DomainTicketClerk extends DomainBase{
    private String email;
    private String password;
}
