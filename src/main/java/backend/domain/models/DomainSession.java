package backend.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainSession extends DomainBase {

    private LocalDateTime startTime;
    private Long movie;
    private Long hall;
    private Double price;

}

