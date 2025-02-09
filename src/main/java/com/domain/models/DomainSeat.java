package com.domain.models;

import jakarta.persistence.*;
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

    private DomainHall hall;

    private String seatType;

}