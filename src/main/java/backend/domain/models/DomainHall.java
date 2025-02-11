package backend.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DomainHall extends DomainBase {

    private String name;
    private Integer capacity;
    private String type;

    // TODO : GET ALL SESSIONS FROM A HALL
    // TODO : GET ALL SEATS FROM A HALL

}
