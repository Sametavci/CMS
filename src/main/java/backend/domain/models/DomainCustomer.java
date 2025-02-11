package backend.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainCustomer extends DomainBase {
    private String name;
    private String surname;
    private int age;
}
