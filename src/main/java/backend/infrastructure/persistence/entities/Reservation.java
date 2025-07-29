package backend.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "reservations")
public class Reservation extends BaseEntity {


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;


}
