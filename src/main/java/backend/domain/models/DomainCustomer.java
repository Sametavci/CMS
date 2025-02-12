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
    private Boolean isSub;
    private String email;


    // TODO : GET SUB CUSTOMERS
    // TODO : MAKE A CUSTOMER SUB
    // TODO : MAKE A SUB AGAIN JUST A CUSTOMER
}
