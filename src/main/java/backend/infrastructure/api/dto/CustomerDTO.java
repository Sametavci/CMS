package backend.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDTO extends BaseDTO {
    private String name;
    private String surname;
    private int age;
    private Boolean isSub;
    private String email;

}