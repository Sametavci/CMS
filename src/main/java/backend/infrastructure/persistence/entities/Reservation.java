package backend.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "reservations")
public class Reservation extends BaseEntity {


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;


}
