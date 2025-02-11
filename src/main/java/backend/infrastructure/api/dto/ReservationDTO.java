package backend.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO extends BaseDTO{
    private Long seats;
    private Long customer;
    private Long sessions;
    private Double price;
}
