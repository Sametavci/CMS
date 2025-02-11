package backend.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainSeat extends DomainBase {

    private String seatRow;

    private int seatColumn;

    private boolean isBooked = false;

    private Long hall;

    private String seatType;

}