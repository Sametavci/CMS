package backend.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainReservation extends DomainBase {


    private Long session;

    private Long customer;

    private Long seat;


}
