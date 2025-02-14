package backend.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "seats")
public class Seat extends BaseEntity {


    @Column(nullable = false)
    private String seatRow;

    @Column(nullable = false)
    private int seatColumn;

    @Column(nullable = false)
    private boolean isBooked = false;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    private String seatType;


}